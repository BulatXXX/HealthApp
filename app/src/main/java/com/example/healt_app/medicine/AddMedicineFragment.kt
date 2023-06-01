package com.example.healt_app.medicine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healt_app.databinding.FragmentAddMedicineBinding
import com.example.healt_app.medicine.medicineRecyclerView.Medicine


class AddMedicineFragment : Fragment() {

    private var _binding : FragmentAddMedicineBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMedicineBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.saveBtn.setOnClickListener {
            val name = binding.medicineName.text.toString()
            val freq = binding.freq.text.toString()
            val time = binding.time.toString()
            val medicine = Medicine(0,name,freq,time)       //Don't know how to use id
            addMedicine(medicine)
        }
    }

    private fun addMedicine(medicine: Medicine) {
        //Uploading into DataBase
    }

}