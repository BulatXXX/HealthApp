package com.example.healt_app.roll_doctor.appointment

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
import com.example.healt_app.RecyclerViewAdapters.PatientsAdapter
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.FragmentChoosingPatientBinding


class ChoosingPatientFragment : Fragment(),PatientsAdapter.Listener {

    private var _binding : FragmentChoosingPatientBinding? = null
    private val binding get() = _binding!!
    private var patientList = ArrayList<User>()
    private val adapter = PatientsAdapter(this)
    private val args: ChoosingPatientFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChoosingPatientBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())

        val animationDrawable = binding.choosingPatientFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        binding.patientRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        db.getDao().getPatients().asLiveData().observe(requireActivity()){
            patientList.clear()
            it.forEach{patient->
                patientList.add(patient)
            }
            adapter.updateList(patientList)
            binding.patientRecyclerView.adapter = adapter
        }

    }

    override fun onClick(user: User) {
        val action = ChoosingPatientFragmentDirections.actionChoosingPatientFragmentToCreateDoctorAppointmentFragment(patient = user, doctor = args.doctor)
        Navigation.findNavController(requireView()).navigate(action)
    }


}