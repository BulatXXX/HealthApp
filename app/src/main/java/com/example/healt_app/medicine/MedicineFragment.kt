package com.example.healt_app.medicine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healt_app.databinding.FragmentHomeScreenBinding
import com.example.healt_app.databinding.FragmentMedicineBinding
import com.example.healt_app.medicine.medicineRecyclerView.Medicine
import com.example.healt_app.medicine.medicineRecyclerView.MedicineAdapter


class MedicineFragment : Fragment(), MedicineAdapter.Listener {
    private var _binding: FragmentMedicineBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val medicineAdapter = MedicineAdapter(this)
    private var medicineList = ArrayList<Medicine>()

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
        getTestMedicineList()
        medicineAdapter.updateList(medicineList)
        binding.medicineRecyclerView.adapter = medicineAdapter
        binding.medicineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.addCardView.setOnClickListener {
            val action = MedicineFragmentDirections.actionMedicineFragmentToAddMedicineFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

    }

    private fun getMedicineList(){
        medicineList.clear()

    }
    private fun getTestMedicineList(){
        for(i in 1..5){
            medicineList.add(Medicine(i,"Витамин $i","Каждый день","${i+1}:00"))
        }

    }

    override fun onClick(medicine: Medicine) {
        val action = MedicineFragmentDirections.actionMedicineFragmentToChangeMedicineFragment(medicine = medicine)
        Navigation.findNavController(requireView()).navigate(action)
    }


}