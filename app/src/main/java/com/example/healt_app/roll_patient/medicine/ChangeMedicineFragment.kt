package com.example.healt_app.roll_patient.medicine

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.DialogMedicineFrequencyPickerBinding
import com.example.healt_app.databinding.DialogMedicineNameBinding
import com.example.healt_app.databinding.FragmentChangeMedicineBinding
import com.example.healt_app.dataBase.Medicine
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class ChangeMedicineFragment : Fragment() {

    private var _binding: FragmentChangeMedicineBinding? = null
    private val binding get() = _binding!!
    private val args: ChangeMedicineFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeMedicineBinding.inflate(inflater , container , false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val args: ChangeMedicineFragmentArgs by navArgs()
        val medicine = args.medicine




        deleteBtnInit(medicine)

        binding.saveBtn.setOnClickListener {
            changeMedicine(medicine)
        }
        binding.medicineName.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder.create()
            val layoutInflater = layoutInflater
            val dialogBinding = DialogMedicineNameBinding.inflate(layoutInflater)

            dialogBinding.saveBtn.setOnClickListener {
                binding.medicineName.text = dialogBinding.medicineName.text
                dialog.dismiss()
            }
            dialog.setView(dialogBinding.root)
            dialog.show()
        }

        binding.freq.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder.create()
            val layoutInflater = layoutInflater
            val dialogBinding = DialogMedicineFrequencyPickerBinding.inflate(layoutInflater)
            dialogBinding.freqPicker.minValue = 0
            dialogBinding.freqPicker.maxValue = 4
            val frequency = arrayOf(
                getString(R.string.three_times_in_a_day) ,
                getString(R.string.twice_a_day) ,
                getString(R.string.every_day) ,
                getString(R.string.once_every_two_days) ,
                getString(R.string.once_every_week)
            )
            dialogBinding.freqPicker.displayedValues = frequency
            dialogBinding.freqPicker.value = frequency.indexOf(binding.freq.text.toString())

            dialogBinding.saveBtn.setOnClickListener {
                binding.freq.text = frequency[dialogBinding.freqPicker.value]
                dialog.dismiss()
            }
            dialog.setView(dialogBinding.root)
            dialog.show()
        }

        binding.time.setOnClickListener {
            val isSystem24Hour = DateFormat.is24HourFormat(requireContext())

            val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(12)
                .setMinute(0)
                .setTitleText(getString(R.string.choose_time))
                .build()
            picker.show(childFragmentManager,"TAG")
            picker.addOnPositiveButtonClickListener {
                binding.time.text = "${ picker.hour.toString()}:${ picker.minute.toString()}"

            }
            picker.addOnNegativeButtonClickListener {
                picker.dismiss()
            }
        }

        medicine.id?.let {
            MainDB.getDb(requireContext()).getDao().getMedicineByID(medicine.id).asLiveData()
                .observe(requireActivity()) { med ->
                    binding.medicineName.text = med.name
                    binding.freq.text = med.frequency
                    binding.time.text = med.time
                }
        }


    }

    private fun deleteBtnInit(medicine: Medicine) {
        binding.deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(R.string.deleting)

            builder.setMessage(getString(R.string.delete_medicine_from_the_list_question))
            builder.setPositiveButton(R.string.ok) { dialogInterface , _ ->
                deleteMedicine(medicine)
                Navigation.findNavController(requireView()).popBackStack()
            }
            builder.setNegativeButton(R.string.cancel) { dialogInterface , _ ->
            }
            val dialog = builder.create()
            dialog.show()

        }
    }

    private fun deleteMedicine(medicine: Medicine) {
        Thread {
            medicine.id?.let { MainDB.getDb(requireContext()).getDao().deleteMedicineById(it) }
        }.start()
    }

    private fun changeMedicine(medicine: Medicine) {

        var name = binding.medicineName.text.toString()
        if (name == "") {
            name = medicine.name
        }
        var freq = binding.freq.text.toString()
        if (freq == "") {
            freq = medicine.frequency
        }
        var time = binding.time.text.toString()
        if (time == "") {
            time = medicine.time
        }
        val changedMedicine = Medicine(medicine.id , name , freq , time , args.userId)


        Toast.makeText(requireContext() , changedMedicine.toString() , Toast.LENGTH_LONG).show()

        changedMedicineInDB(changedMedicine)


        Navigation.findNavController(requireView()).popBackStack()
    }

    private fun changedMedicineInDB(medicine: Medicine) {
        Thread {
            MainDB.getDb(requireContext()).getDao().updateMedicine(medicine)
        }.start()
    }


}