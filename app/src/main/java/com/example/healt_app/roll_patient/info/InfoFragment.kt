package com.example.healt_app.roll_patient.info

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.dataBase.User

import com.example.healt_app.databinding.FragmentInfoBinding

import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.Period
import kotlin.math.pow

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInfoBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        val args: InfoFragmentArgs by navArgs()
        val user = args.user
        val db = MainDB.getDb(requireContext())

        val animationDrawable = binding.infoFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()


        user.id?.let {
            db.getDao().getUserByID(it).asLiveData().observe(requireActivity()) { user ->
                initPersonalInfo(user)
            }
        }


        binding.editInfoBtn.setOnClickListener {
            val action = user.id?.let { it1 ->
                InfoFragmentDirections.actionInfoFragmentToChangeInfoFragment(userId = it1)
            }
            action?.let { it1 -> Navigation.findNavController(requireView()).navigate(it1) }
        }
        binding.helpBtn.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToMassIndexFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.logOutBtn.setOnClickListener {
            val sPref = requireActivity().getPreferences(Activity.MODE_PRIVATE)
            val ed = sPref.edit()
            ed.putInt("ID" , -1)
            ed.commit()
            val action = InfoFragmentDirections.actionInfoFragmentToLogInFragment()
            Navigation.findNavController(requireView()).navigate(action)


        }


    }

    private fun countMassIndex(height: Int , weight: Double): String {
        val massIndex = weight / ((height.toDouble() / 100).pow(2))
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val massIndexString = df.format(massIndex).replace("," , ".")
        return massIndexString
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

    private fun initPersonalInfo(user: User) {
        binding.nameTextView.text = user.name
        val height = user.height
        val weight = user.weight
        binding.heightValue.text = if (height != 0) height.toString() else {
            "N/d"
        }
        binding.weightValue.text = if (weight != 0.0) weight.toString() else {
            "N/d"
        }
        binding.ageValue.text = countAge(user.birthDate).toString()

        binding.massIndexValue.text = user.height?.let {
            user.weight?.let { weight ->
                countMassIndex(
                    it ,
                    weight
                )
            }
        }
        binding.massIndexResult.text = if (height != 0 && weight !=0.0) getMassIndexResult() else {
            "N/d"
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun getMassIndexResult(): String {
        val massIndex = binding.massIndexValue.text.toString().toDouble()
        val color = ContextCompat.getColor(requireContext() , R.color.pink)
        if (massIndex < 18) {
            binding.deficitTitle.setTextColor(color)
            return getString(R.string.deficite)
        } else if (massIndex < 25) {
            binding.normalTitle.setTextColor(color)
            return getString(R.string.normal)
        } else if (massIndex < 30) {
            binding.overweightTitle.setTextColor(color)
            return getString(R.string.overweight)

        } else if (massIndex < 35) {
            binding.obesity1Title.setTextColor(color)
            return getString(R.string.obesity_1_dg)

        } else if (massIndex < 40) {
            binding.obesity2Title.setTextColor(color)
            return getString(R.string.obesity_2_dg)

        } else {
            binding.obesity3Title.setTextColor(color)
            return getString(R.string.obesity_3_dg)

        }

    }



}