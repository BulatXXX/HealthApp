package com.example.healt_app.roll_doctor

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.databinding.FragmentHomeDoctorBinding


class HomeDoctorFragment : Fragment() {
    private var _binding: FragmentHomeDoctorBinding? = null
    private val binding get() = _binding!!
    private val args: HomeDoctorFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeDoctorBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        val animationDrawable = binding.homeDoctorFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        binding.infoCardView.setOnClickListener {
            val action = HomeDoctorFragmentDirections.actionHomeDoctorFragmentToDoctorInfoFragment(args.doctor.id!!)
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.patientsCardView.setOnClickListener {
            val action = HomeDoctorFragmentDirections.actionHomeDoctorFragmentToPatientsFragment(args.doctor)
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.doctorsAppointmentCardView.setOnClickListener {
            val action = HomeDoctorFragmentDirections.actionHomeDoctorFragmentToAppointsmentsFragment(args.doctor)
            Navigation.findNavController(requireView()).navigate(action)
        }
    }


}