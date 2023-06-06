package com.example.healt_app.loggining


import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {


    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)



        binding.registrationBtn.setOnClickListener {
            if (
                binding.logInEt.text.toString() != "" && binding.passwordEt.text.toString() != "" && binding.birthDayPicker.text.toString() != "" && binding.nameEt.text.toString() != "" && isValidDate(
                    binding.birthDayPicker.text.toString()
                )
            ) {
                val user = User(
                    null ,
                    binding.logInEt.text.toString() ,
                    binding.passwordEt.text.toString() ,
                    binding.nameEt.text.toString() ,
                    binding.birthDayPicker.text.toString() ,
                    binding.checkBox.isChecked
                )
                if(user.roll){
                    val action = RegistrationFragmentDirections.actionRegistrationFragmentToDoctorsRegistrationFragment(user = user)
                    Navigation.findNavController(requireView()).navigate(action)
                }else{
                    val action = RegistrationFragmentDirections.actionRegistrationFragmentToPatientsRegistrationFragment(user = user)
                    Navigation.findNavController(requireView()).navigate(action)
                }


                //Getting back to LoginFragment
                Toast.makeText(
                    requireContext() ,
                    R.string.successfully_registred ,
                    Toast.LENGTH_SHORT
                ).show()

            } else if (!isValidDate(binding.birthDayPicker.text.toString())) {
                var toast = Toast.makeText(
                    requireContext() ,
                    "R.string. " ,
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER , 0 , 0)
                toast.show()
            } else {
                var toast = Toast.makeText(
                    requireContext() ,
                    R.string.enter_missing_info ,
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER , 0 , 0)
                toast.show()

            }
        }


    }

    fun isValidDate(date: String): Boolean {
        val pattern = "^(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.]\\d{4}\$"
        return pattern.toRegex().matches(date)
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}