package com.example.healt_app.roll_patient.medicine

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
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.FragmentMedicineBinding
import com.example.healt_app.dataBase.Medicine

import com.example.healt_app.RecyclerViewAdapters.MedicineAdapter


class MedicineFragment : Fragment(), MedicineAdapter.Listener {
    private var _binding: FragmentMedicineBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val medicineAdapter = MedicineAdapter(this)
    private var medicineList = ArrayList<Medicine>()
    val args : MedicineFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMedicineBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        val db = MainDB.getDb(requireContext())

        val animationDrawable = binding.medicineFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        db.getDao().getMedicineByPatientId(args.userId).asLiveData().observe(requireActivity()){list->
            medicineList.clear()
            list.forEach{
                medicineList.add(it)
            }
            medicineAdapter.updateList(medicineList)
            binding.medicineRecyclerView.adapter = medicineAdapter
        }
        medicineAdapter.updateList(medicineList)
        binding.medicineRecyclerView.adapter = medicineAdapter
        binding.medicineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.addCardView.setOnClickListener {
            val action =
                MedicineFragmentDirections.actionMedicineFragmentToAddMedicineFragment(userId = args.userId)
            Navigation.findNavController(requireView()).navigate(action)
        }


    }



    override fun onClick(medicine: Medicine) {

        val action = MedicineFragmentDirections.actionMedicineFragmentToChangeMedicineFragment(
            medicine = medicine ,
            userId = args.userId
        )
        Navigation.findNavController(requireView()).navigate(action)
    }


}