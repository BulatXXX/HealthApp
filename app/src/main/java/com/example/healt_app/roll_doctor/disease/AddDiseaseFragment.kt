package com.example.healt_app.roll_doctor.disease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.RecyclerViewAdapters.DiseasesAdapter
import com.example.healt_app.dataBase.Disease
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentAddDiseaseBinding
import com.example.healt_app.databinding.FragmentDisesesBinding


class AddDiseaseFragment : Fragment(){
    private var _binding: FragmentAddDiseaseBinding? = null
    private val binding get() = _binding!!
    private val args: AddDiseaseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddDiseaseBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.patientName.text = args.patient.name
        val db = MainDB.getDb(requireContext())
        binding.saveBtn.setOnClickListener {
            if(validation()){
            Thread{
                val disease = Disease(id = null, name = binding.diseaseName.text.toString(),args.doctor.id!!,args.doctor.name.toString(),binding.description.text.toString(),args.patient.id!!)
                db.getDao().insertDisease(disease = disease)
            }.start()}


        }
    }

    private fun validation(): Boolean {
        return true
    }


}