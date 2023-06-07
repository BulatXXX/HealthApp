package com.example.healt_app.roll_patient.doctor_appointment

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healt_app.RecyclerViewAdapters.AppointmentAdapter
import com.example.healt_app.databinding.FragmentDoctorsBinding
import com.example.healt_app.dataBase.Appointment
import com.example.healt_app.dataBase.MainDB

class DoctorsFragment : Fragment() ,AppointmentAdapter.Listener{

    private var _binding: FragmentDoctorsBinding? = null

    private val binding get() = _binding!!
    private val args :DoctorsFragmentArgs by navArgs()
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
        val db = MainDB.getDb(requireContext())
        val animationDrawable = binding.doctorsFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
        binding.doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        db.getDao().getAppointmentsByPatientId(args.patientId).asLiveData().observe(requireActivity()){
            appointmentsList.clear()
            it.forEach { appointment ->
                appointmentsList.add(appointment)
            }
            appointmentAdapter.updateList(appointmentsList)
            binding.doctorsRecyclerView.adapter = appointmentAdapter
        }
    }


    override fun onClick(appointment: Appointment) {
        //Nothing cause created fo doctors
    }


}