package com.example.healt_app.roll_doctor.appointment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.Appointment
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentCreateDoctorAppointmentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*


class CreateDoctorAppointmentFragment : Fragment() {

    private var _binding: FragmentCreateDoctorAppointmentBinding? = null
    private val binding get() = _binding!!
    private val args: CreateDoctorAppointmentFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentCreateDoctorAppointmentBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())
        binding.patientName.text = args.patient.name
        binding.time.setOnClickListener {
            val isSystem24Hour = is24HourFormat(requireContext())

            val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(12)
                .setMinute(0)
                .setTitleText(getString(R.string.choose_appointment_time))
                .build()
            picker.show(childFragmentManager,"TAG")
            picker.addOnPositiveButtonClickListener {
                binding.time.text = "${ picker.hour.toString()}:${ picker.minute.toString()}"

            }

            picker.addOnNegativeButtonClickListener {
                picker.dismiss()
            }
        }
        binding.date.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.select_birth_date)
                .setSelection((MaterialDatePicker.todayInUtcMilliseconds()))
                .build()
            picker.addOnPositiveButtonClickListener {
                val dateSelected = SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(it)
                binding.date.text = dateSelected.toString()
            }
            picker.addOnNegativeButtonClickListener {
                picker.dismiss()
            }
            picker.show(requireActivity().supportFragmentManager,"DatePicker")
        }
        binding.saveBtn.setOnClickListener {
            if (validation()) {
                Thread {
                    val appointment = Appointment(
                        null ,
                        args.doctor.id!! ,
                        args.patient.id!! ,
                        args.doctor.name ,
                        args.patient.name ,
                        time = binding.time.text.toString() ,
                        date = binding.date.text.toString() ,
                        post = args.doctor.post.toString()
                    )
                    db.getDao().insertAppointment(appointment = appointment)
                }.start()
                Toast.makeText(requireContext(),R.string.successfully_created,Toast.LENGTH_SHORT).show()
                val action = CreateDoctorAppointmentFragmentDirections.actionCreateDoctorAppointmentFragmentToAppointsmentsFragment(args.doctor)
                Navigation.findNavController(requireView()).navigate(action)


            } else {
                Toast.makeText(requireContext(),R.string.error_try_again,Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun validation(): Boolean {
        return true
    }


}