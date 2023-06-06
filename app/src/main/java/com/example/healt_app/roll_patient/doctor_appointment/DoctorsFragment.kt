package com.example.healt_app.roll_patient.doctor_appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healt_app.RecyclerViewsAdapters.AppointmentAdapter
import com.example.healt_app.databinding.FragmentDoctorsBinding
import com.example.healt_app.dataBase.Appointment

class DoctorsFragment : Fragment() ,AppointmentAdapter.Listener{

    private var _binding: FragmentDoctorsBinding? = null

    private val binding get() = _binding!!
    private var appointmentsList = ArrayList<Appointment>()
    private val appointmentAdapter = AppointmentAdapter(this,false)


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
        getListFromDB(appointmentsList)
        appointmentAdapter.updateList(appointmentsList)
        binding.doctorsRecyclerView.adapter = appointmentAdapter
        binding.doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun getDoctorAppointmentList(){

    }
    private fun getListFromDB(arrayList: ArrayList<Appointment>) {
        for (i in 1..5) {
            val appointment = Appointment(
                id = i ,
                doctorName = "Doctor $i" ,
                patientName = "Patient 0${i * 20}" ,
                doctorId = i,
                patientId = i*20,
                time = "1$i:$i$i",
                date = "Day $i",
                post = "Medic $i Medic"
            )
            arrayList.add(appointment)
        }
    }

    override fun onClick(appointment: Appointment) {
        //Nothing cause created fo doctors
    }


}