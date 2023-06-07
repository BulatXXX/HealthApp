package com.example.healt_app.roll_doctor.appointment

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healt_app.RecyclerViewAdapters.AppointmentAdapter
import com.example.healt_app.dataBase.Appointment
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentAppointsmentsBinding


class AppointsmentsFragment : Fragment(), AppointmentAdapter.Listener {

    private var _binding: FragmentAppointsmentsBinding? = null
    private val binding get() = _binding!!
    private val args: AppointsmentsFragmentArgs by navArgs()
    private var appointmentsList = ArrayList<Appointment>()
    private val appointmentAdapter = AppointmentAdapter(this,true)
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAppointsmentsBinding.inflate(layoutInflater , container , false)
        return binding.root

    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())

        val animationDrawable = binding.appointsmentsFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        db.getDao().getAppointmentsByDoctorId(args.doctor.id!!).asLiveData().observe(requireActivity()){
            appointmentsList.clear()
            it.forEach{appointment->
                appointmentsList.add(appointment)
            }
            appointmentAdapter.updateList(appointmentsList)
            binding.appointmentRecyclerView.adapter = appointmentAdapter
        }
        binding.addCardView.setOnClickListener {
            val action =  AppointsmentsFragmentDirections.actionAppointsmentsFragmentToChoosingPatientFragment(args.doctor)
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    override fun onClick(appointment: Appointment) {

    }


}