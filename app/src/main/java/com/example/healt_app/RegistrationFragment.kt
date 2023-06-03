package com.example.healt_app


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.FragmentRegistrationBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


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

    private fun registerNewUser(user: User) {
        //register to DB
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.registrationBtn.setOnClickListener {

        }
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val date = LocalDate.now().format(formatter)
        binding.birthDayPicker.hint = date.toString()
    }


}