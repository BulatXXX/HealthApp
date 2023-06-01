package com.example.healt_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.healt_app.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {


    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogInBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val toast: Toast = Toast.makeText(requireContext(),"Logged",Toast.LENGTH_SHORT)
        binding.logInBtn.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToHomeScreenFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.registrationBtn.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToRegistrationFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        toast.show()

    }


}