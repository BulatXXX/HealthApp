package com.example.healt_app.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.healt_app.InfoFragmentDirections
import com.example.healt_app.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.editInfoBtn.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToChangeInfoFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.helpBtn.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToMassIndexFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }


}