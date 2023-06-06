package com.example.healt_app.roll_patient.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.FragmentChangeInfoBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class ChangeInfoFragment : Fragment() {

    private var _binding: FragmentChangeInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangeInfoBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val args : ChangeInfoFragmentArgs by navArgs()
        val db = MainDB.getDb(requireContext())
        binding.birthDateEditText.setOnClickListener{
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.select_birth_date)
                .setSelection((MaterialDatePicker.todayInUtcMilliseconds()))
                .build()
            picker.addOnPositiveButtonClickListener {
                val dateSelected = SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(it)
                binding.birthDateEditText.text = dateSelected
            }
            picker.addOnNegativeButtonClickListener {
                picker.dismiss()
            }
            picker.show(requireActivity().supportFragmentManager,"DatePicker")
        }
        db.getDao().getUserByID(args.userId).asLiveData().observe(requireActivity()){
            with(binding){
                nameEditText.hint = it.name
                birthDateEditText.hint = it.birthDate
                loginEditText.hint = it.login
                passwordEditText.hint = it.password
                weightEditText.hint = it.weight.toString()
                heightEditText.hint = it.height.toString()

            }
        }
        binding.saveBtn.setOnClickListener {

            with(binding){
                if(isValidDate(birthDateEditText.text.toString()) || birthDateEditText.text.toString().isEmpty()){
                val name = if(nameEditText.text.toString().isEmpty())nameEditText.hint else{nameEditText.text}
                val login = if(loginEditText.text.toString().isEmpty())loginEditText.hint else{loginEditText.text}
                val password = if(passwordEditText.text.toString().isEmpty())passwordEditText.hint else{passwordEditText.text}
                val birthday = if(birthDateEditText.text.toString().isEmpty())birthDateEditText.hint else{birthDateEditText.text}
                val weight = if(weightEditText.text.toString().isEmpty())weightEditText.hint else{weightEditText.text}
                val height = if(heightEditText.text.toString().isEmpty())heightEditText.hint else{heightEditText.text}
                val user = User(args.userId, login.toString(),password.toString(),name.toString(), birthday.toString(), roll = false, post = null,weight.toString().toDouble(),height.toString().toInt())
                Thread{db.getDao().updateUser(user)}.start()
                Navigation.findNavController(requireView()).popBackStack()
                Toast.makeText(requireContext(),R.string.changed,Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(requireContext(),R.string.wrong_birth_date,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    fun isValidDate(date: String): Boolean {
        val pattern = "^(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.]\\d{4}\$"
        return pattern.toRegex().matches(date)
    }


}