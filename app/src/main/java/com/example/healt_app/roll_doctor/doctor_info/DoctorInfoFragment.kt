package com.example.healt_app.roll_doctor.doctor_info

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentDoctorInfoBinding
import com.example.healt_app.databinding.FragmentHomeDoctorBinding
import java.time.LocalDate
import java.time.Period


class DoctorInfoFragment : Fragment() {
    private var _binding: FragmentDoctorInfoBinding? = null
    private val binding get() = _binding!!
    val args: DoctorInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDoctorInfoBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())

        val animationDrawable = binding.doctorInfoFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        db.getDao().getUserByID(args.doctorId).asLiveData().observe(requireActivity()){
            binding.ageValue.text = countAge(it.birthDate).toString()
            binding.postValue.text = it.post.toString()
            binding.nameTextView.text = it.name
        }
        binding.editInfoBtn.setOnClickListener {

            val action = DoctorInfoFragmentDirections.actionDoctorInfoFragmentToChangeDoctorInfoFragment(args.doctorId)
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.logOutBtn.setOnClickListener {
            val sPref = requireActivity().getPreferences(Activity.MODE_PRIVATE)
            val ed = sPref.edit()
            ed.putInt("ID" , -1)
            ed.commit()
            val action = DoctorInfoFragmentDirections.actionDoctorInfoFragmentToLogInFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

    }
    private fun countAge(birthDate: String): Int {
        val currentDate = LocalDate.now()
        val birth_date = LocalDate.of(
            birthDate.substring(6).toInt() ,
            birthDate.substring(3 , 5).toInt() ,
            birthDate.substring(0 , 2).toInt()
        )
        return Period.between(birth_date , currentDate).years
    }


}