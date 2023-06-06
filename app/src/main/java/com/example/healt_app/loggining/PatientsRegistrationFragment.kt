package com.example.healt_app.loggining

import android.os.Bundle
import android.text.style.LineHeightSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentLogInBinding
import com.example.healt_app.databinding.FragmentPatientsRegistrationBinding


class PatientsRegistrationFragment : Fragment() {
    private var _binding: FragmentPatientsRegistrationBinding? = null
    private val binding get() = _binding!!
    private val args: PatientsRegistrationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPatientsRegistrationBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())
        binding.registrationBtn.setOnClickListener {

            val user = args.user
            if (validation(
                    binding.weightEditText.text.toString() ,
                    binding.heightEditText.text.toString()
                )
            ) {
                val weight = binding.weightEditText.text.toString().toDouble()
                val height = binding.heightEditText.text.toString().toInt()
                user.weight = weight
                user.height = height
                Thread { db.getDao().insertUser(user) }.start()

            }

            val action =
                PatientsRegistrationFragmentDirections.actionPatientsRegistrationFragmentToLogInFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

    }

    private fun validation(weight: String , height: String): Boolean {
        return true
    }

}