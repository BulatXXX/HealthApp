package com.example.healt_app.roll_patient

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

import com.example.healt_app.databinding.FragmentHomeScreenBinding
import com.google.android.material.snackbar.Snackbar


class HomeScreenFragment : Fragment() {


    private var _binding: FragmentHomeScreenBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeScreenBinding.inflate(inflater , container , false)

        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {

        val args : HomeScreenFragmentArgs by navArgs()
        val user = args.user



        val animationDrawable = binding.homeScreenFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
        binding.categoriesCardView.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToCategoriesFragment(patient = args.user)
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.infoCardView.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToInfoFragment(user)
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.doctorsAppointmentCardView.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToDoctorsFragment(args.user.id!!)
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.medicineCardView.setOnClickListener {
            val action = user.id?.let { it1 ->
                HomeScreenFragmentDirections.actionHomeScreenFragmentToMedicineFragment(
                    userId = it1
                )
            }
            action?.let { it1 -> Navigation.findNavController(requireView()).navigate(it1) }
        }

        super.onViewCreated(view , savedInstanceState)
    }


}