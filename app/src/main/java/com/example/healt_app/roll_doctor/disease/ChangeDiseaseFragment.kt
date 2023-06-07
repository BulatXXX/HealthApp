package com.example.healt_app.roll_doctor.disease

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.Disease
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentChangeDiseaseBinding


class ChangeDiseaseFragment : Fragment() {
    private var _binding: FragmentChangeDiseaseBinding? = null
    private val binding get() = _binding!!
    private val args: ChangeDiseaseFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangeDiseaseBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.patientName.text = args.disease.doctorName
        binding.diseaseName.hint = args.disease.name
        binding.description.hint = args.disease.description
        val db = MainDB.getDb(requireContext())
        binding.saveBtn.setOnClickListener {
            val disease = Disease(
                args.disease.id ,
                binding.diseaseName.text.toString() ,
                args.disease.doctorId ,
                args.disease.doctorName ,
                binding.description.text.toString(),
                args.disease.patientId
            )
            Thread{
                db.getDao().updateDisease(disease)
            }.start()
        }
        deleteBtnInit(args.disease)
    }
    private fun deleteBtnInit(disease: Disease) {
        binding.deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(R.string.deleting)

            builder.setMessage(getString(R.string.delete_info))
            builder.setPositiveButton(R.string.ok) { dialogInterface , _ ->
                deleteDisease(disease)
                Navigation.findNavController(requireView()).popBackStack()
            }
            builder.setNegativeButton(R.string.cancel) { dialogInterface , _ ->
            }
            val dialog = builder.create()
            dialog.show()

        }
    }

    private fun deleteDisease(disease: Disease) {
        Thread {
            MainDB.getDb(requireContext()).getDao().deleteDisease(disease)
        }.start()
    }


}