package com.example.healt_app.roll_doctor.doctor_info

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.DialogPostPickerBinding
import com.example.healt_app.databinding.FragmentChangeDoctorInfoBinding
import com.example.healt_app.databinding.FragmentChangeInfoBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class ChangeDoctorInfoFragment : Fragment() {

    private var _binding: FragmentChangeDoctorInfoBinding? = null
    private val binding get() = _binding!!
    private val args: ChangeDoctorInfoFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangeDoctorInfoBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())
        db.getDao().getUserByID(args.doctorId).asLiveData().observe(requireActivity()) {
            binding.nameEditText.hint = it.name
            binding.loginEditText.hint = it.login
            binding.passwordEditText.hint = it.password
            binding.birthDateEditText.hint = it.birthDate
            binding.postPicker.hint = it.post
        }
        binding.birthDateEditText.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.select_birth_date)
                .setSelection((MaterialDatePicker.todayInUtcMilliseconds()))
                .build()
            picker.addOnPositiveButtonClickListener {
                val dateSelected = SimpleDateFormat("dd.MM.YYYY" , Locale.getDefault()).format(it)
                binding.birthDateEditText.text = dateSelected
            }
            picker.addOnNegativeButtonClickListener {
                picker.dismiss()
            }
            picker.show(requireActivity().supportFragmentManager , "DatePicker")
        }
        binding.postPicker.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder.create()
            val layoutInflater = layoutInflater
            val dialogBinding = DialogPostPickerBinding.inflate(layoutInflater)
            val posts = resources.getStringArray(R.array.Posts)
            dialogBinding.freqPicker.minValue = 0
            dialogBinding.freqPicker.maxValue = posts.size - 1
            dialogBinding.freqPicker.displayedValues = posts
            dialogBinding.saveBtn.setOnClickListener {
                binding.postPicker.text = posts[dialogBinding.freqPicker.value]
                dialog.dismiss()
            }
            dialog.setView(dialogBinding.root)
            dialog.show()
        }
        binding.saveBtn.setOnClickListener {
            with(binding) {

                val name = if (nameEditText.text.toString().isEmpty()) nameEditText.hint else {
                    nameEditText.text
                }
                val login = if (loginEditText.text.toString().isEmpty()) loginEditText.hint else {
                    loginEditText.text
                }
                val password =
                    if (passwordEditText.text.toString().isEmpty()) passwordEditText.hint else {
                        passwordEditText.text
                    }
                val birthday =
                    if (birthDateEditText.text.toString().isEmpty()) birthDateEditText.hint else {
                        birthDateEditText.text
                    }
                val post = if (postPicker.text.toString().isEmpty()) postPicker.hint else {
                    postPicker.text
                }
                val user = User(
                    args.doctorId ,
                    login.toString() ,
                    password.toString() ,
                    name.toString() ,
                    birthday.toString() ,
                    roll = false ,
                    post = post.toString()
                )
                Thread { db.getDao().updateUser(user) }.start()
                Navigation.findNavController(requireView()).popBackStack()
                Toast.makeText(requireContext() , R.string.changed , Toast.LENGTH_SHORT).show()
            }
        }
    }
}

