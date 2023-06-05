package com.example.healt_app.roll_patient.doctor_appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healt_app.R
import com.example.healt_app.databinding.FragmentDoctorsBinding
import com.example.healt_app.roll_patient.doctor_appointment.doctorRecyclerView.DoctorAppointment
import com.example.healt_app.roll_patient.doctor_appointment.doctorRecyclerView.DoctorAppointmentAdapter

class DoctorsFragment : Fragment() {

    private var _binding: FragmentDoctorsBinding? = null

    private val binding get() = _binding!!
    private var doctorAppointmentList = ArrayList<DoctorAppointment>()
    private val doctorAppointmentAdapter = DoctorAppointmentAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDoctorsBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        getDoctorAppointmentList()
        doctorAppointmentAdapter.updateList(doctorAppointmentList)
        binding.doctorsRecyclerView.adapter = doctorAppointmentAdapter
        binding.doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun getDoctorAppointmentList(){
        for (i in 1..6){
            doctorAppointmentList.add(DoctorAppointment(i,"Doctor ${i}","$i",i.toString(),i.toString()))
        }
    }


}