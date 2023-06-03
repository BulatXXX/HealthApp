package com.example.healt_app.loggining

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation

import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.loggining.LogInFragmentDirections
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

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val toast: Toast = Toast.makeText(requireContext() , "Logged" , Toast.LENGTH_SHORT)
        val db = MainDB.getDb(requireContext())
        binding.logInBtn.setOnClickListener {
            var id : Int? = null
            Thread{id = db.getDao()
                .getUserId(binding.logInEt.text.toString() , binding.passwordEt.text.toString())}.start()

            if(id != null){
                val action = LogInFragmentDirections.actionLogInFragmentToHomeScreenFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
            if(id == null){
                if(binding.logInEt.text.toString().isEmpty()){
                   // Toast.makeText(requireContext(), R.id.enter_login,Toast.LENGTH_SHORT).show()
                }
                else if (binding.passwordEt.text.toString().isEmpty()){
                    //Toast.makeText(requireContext(), R.id.enter_password,Toast.LENGTH_SHORT).show()
                }else{
                    //Toast.makeText(requireContext(), R.id.wrong_login_or_password,Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.registrationBtn.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToRegistrationFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        toast.show()

    }


}