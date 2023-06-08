package com.example.healt_app.roll_patient


import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.healt_app.RecyclerViewAdapters.DiseasesAdapter
import com.example.healt_app.dataBase.Disease
import com.example.healt_app.dataBase.MainDB

import com.example.healt_app.databinding.FragmentPatientDiseasesBinding
import com.example.healt_app.roll_doctor.disease.DoctorDiseasesFragmentArgs


class DiseasesFragment : Fragment() ,DiseasesAdapter.Listener{
    private var _binding: FragmentPatientDiseasesBinding? = null
    private val binding get() = _binding!!
    private val args: DiseasesFragmentArgs by navArgs()
    private var diseasesList = ArrayList<Disease>()
    private var adapter = DiseasesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPatientDiseasesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())

        val animationDrawable = binding.diseasesFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        binding.diseasesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        db.getDao().getDiseasesByPatientId(args.patient.id!!).asLiveData().observe(requireActivity()){
            diseasesList.clear()
            it.forEach{
                    disease ->
                diseasesList.add(disease)
            }
            adapter.updateList(diseasesList)
            binding.diseasesRecyclerView.adapter = adapter
        }

    }

    override fun onClick(disease: Disease) {
        //Nothing
    }


}