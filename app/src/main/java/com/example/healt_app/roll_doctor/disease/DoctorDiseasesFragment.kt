package com.example.healt_app.roll_doctor.disease

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healt_app.R
import com.example.healt_app.RecyclerViewAdapters.DiseasesAdapter
import com.example.healt_app.dataBase.Disease
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentCreateDoctorAppointmentBinding
import com.example.healt_app.databinding.FragmentDisesesBinding
import com.example.healt_app.roll_doctor.appointment.CreateDoctorAppointmentFragmentArgs


class DoctorDiseasesFragment : Fragment(),DiseasesAdapter.Listener {
    private var _binding: FragmentDisesesBinding? = null
    private val binding get() = _binding!!
    private val args: DoctorDiseasesFragmentArgs by navArgs()
    private var diseasesList = ArrayList<Disease>()
    private var adapter = DiseasesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDisesesBinding.inflate(layoutInflater,container  ,false  )
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.patientName.text = args.patient.name
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

        binding.addCardView.setOnClickListener {
            val action = DoctorDiseasesFragmentDirections.actionDiseasesFragment2ToAddDiseaseFragment(doctor = args.doctor, patient = args.patient)
            Navigation.findNavController(requireView()).navigate(action)
        }

    }

    override fun onClick(disease: Disease) {
        val action = DoctorDiseasesFragmentDirections.actionDiseasesFragment2ToChangeDiseaseFragment(disease = disease)
        Navigation.findNavController(requireView()).navigate(action)
    }


}