package com.example.healt_app.medicine

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.healt_app.R
import com.example.healt_app.databinding.DialogMedicineFrequencyPickerBinding
import com.example.healt_app.databinding.DialogMedicineNameBinding
import com.example.healt_app.databinding.DialogTimePickerBinding
import com.example.healt_app.databinding.FragmentAddMedicineBinding
import com.example.healt_app.medicine.medicineRecyclerView.Medicine


class AddMedicineFragment : Fragment() {

    private var _binding : FragmentAddMedicineBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMedicineBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
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

            dialogBinding.saveBtn.setOnClickListener {
                binding.freq.text = frequency[dialogBinding.freqPicker.value]
                dialog.dismiss()
            }
            dialog.setView(dialogBinding.root)
            dialog.show()
        }
        binding.time.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder.create()
            val layoutInflater = layoutInflater
            val dialogBinding = DialogTimePickerBinding.inflate(layoutInflater)
            val minutes = arrayOf(
                "00" ,
                "05" ,
                "10" ,
                "15" ,
                "20" ,
                "25" ,
                "30" ,
                "35" ,
                "40" ,
                "45" ,
                "50" ,
                "55"
            )
            dialogBinding.minutesPicker.minValue = 0
            dialogBinding.minutesPicker.maxValue = 11
            dialogBinding.minutesPicker.displayedValues = minutes
            dialogBinding.minutesPicker.value =
                minutes.indexOf(binding.time.text.toString().substring(3))

            val hours = arrayOf(
                "00" ,
                "01" ,
                "02" ,
                "03" ,
                "04" ,
                "05" ,
                "06" ,
                "07" ,
                "08" ,
                "09" ,
                "10" ,
                "11" ,
                "12" ,
                "13" ,
                "14" ,
                "15" ,
                "16" ,
                "17" ,
                "18" ,
                "19" ,
                "20" ,
                "21" ,
                "22" ,
                "23"
            )

            dialogBinding.hourPicker.minValue = 0
            dialogBinding.hourPicker.maxValue = 23
            dialogBinding.hourPicker.displayedValues = hours


            dialogBinding.saveBtn.setOnClickListener {
                binding.time.text =
                    hours[dialogBinding.hourPicker.value] + ":" + minutes[dialogBinding.minutesPicker.value]
                dialog.dismiss()
            }
            dialog.setView(dialogBinding.root)
            dialog.show()
        }

        binding.saveBtn.setOnClickListener {
            val name = binding.medicineName.text.toString()
            val freq = binding.freq.text.toString()
            val time = binding.time.toString()
            val medicine = Medicine(0,name,freq,time)       //Don't know how to use id
            addMedicine(medicine)
            Navigation.findNavController(requireView()).popBackStack()
        }
    }

    private fun addMedicine(medicine: Medicine) {
        //Uploading into DataBase
    }

}