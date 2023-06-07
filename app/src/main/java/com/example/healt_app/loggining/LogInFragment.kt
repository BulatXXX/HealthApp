package com.example.healt_app.loggining

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import com.example.healt_app.R

import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.dataBase.User
import com.example.healt_app.loggining.LogInFragmentDirections
import com.example.healt_app.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {


    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentLogInBinding.inflate(inflater , container , false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val animationDrawable = binding.logInFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
        val db = MainDB.getDb(requireContext())
        val sharedPref = requireActivity().getPreferences(Activity.MODE_PRIVATE)
        val id = sharedPref.getInt("ID" , -1)
        if (id != -1) {
            db.getDao().getUserByID(id).asLiveData().observe(requireActivity()) {
                if (it.roll) {
                    val action =
                        LogInFragmentDirections.actionLogInFragmentToHomeDoctorFragment(it)
                    Navigation.findNavController(requireView()).navigate(action)

                } else {
                    val action =
                        LogInFragmentDirections.actionLogInFragmentToHomeScreenFragment(it)
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
        }
        binding.logInBtn.setOnClickListener {
            var id: Int? = null
            val login = binding.logInEt.text.toString()
            val password = binding.passwordEt.text.toString()




            db.getDao().getUser(login , password).asLiveData().observe(requireActivity()) {
                if (it != null) {
                    val sPref = requireActivity().getPreferences(Activity.MODE_PRIVATE)
                    val ed = sPref.edit()
                    it.id?.let { it1 -> ed.putInt("ID" , it1) }
                    ed.commit()
                    if (it.roll) {
                        val action =
                            LogInFragmentDirections.actionLogInFragmentToHomeDoctorFragment(it)
                        Navigation.findNavController(requireView()).navigate(action)

                    } else {
                        val action =
                            LogInFragmentDirections.actionLogInFragmentToHomeScreenFragment(it)
                        Navigation.findNavController(requireView()).navigate(action)
                    }
                } else {
                    if (binding.logInEt.text.toString().isEmpty()) {
                        Toast.makeText(requireContext() , R.string.enter_login , Toast.LENGTH_SHORT)
                            .show()
                    } else if (binding.passwordEt.text.toString().isEmpty()) {
                        Toast.makeText(
                            requireContext() ,
                            R.string.enter_password ,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext() ,
                            R.string.wrong_login_or_password ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }


        }

        binding.registrationBtn.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToRegistrationFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }


        binding.welcome.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToTestFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }


    }

}