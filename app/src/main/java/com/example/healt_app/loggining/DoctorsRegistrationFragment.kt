package com.example.healt_app.loggining

import android.app.AlertDialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.databinding.DialogPostPickerBinding
import com.example.healt_app.databinding.FragmentDoctorsRegistrationBinding
import com.example.healt_app.databinding.FragmentPatientsRegistrationBinding


class DoctorsRegistrationFragment : Fragment() {
    private var _binding: FragmentDoctorsRegistrationBinding? = null
    private val binding get() = _binding!!
    private val args : DoctorsRegistrationFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDoctorsRegistrationBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        val animationDrawable = binding.doctorsRegistrationFragment.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        val db = MainDB.getDb(requireContext())
        binding.pickPostBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder.create()
            val layoutInflater = layoutInflater
            val dialogBinding = DialogPostPickerBinding.inflate(layoutInflater)
            val posts = resources.getStringArray(R.array.Posts)
            dialogBinding.freqPicker.minValue = 0
            dialogBinding.freqPicker.maxValue = posts.size-1
            dialogBinding.freqPicker.displayedValues = posts
            dialogBinding.saveBtn.setOnClickListener {
                binding.post.text = posts[dialogBinding.freqPicker.value]
                dialog.dismiss()
            }
            dialog.setView(dialogBinding.root)
            dialog.show()

        }
        binding.registrationBtn.setOnClickListener {
            val user = args.user
            user.post = binding.post.text.toString()
            Thread{
                db.getDao().insertUser(user)
            }.start()
            val action = DoctorsRegistrationFragmentDirections.actionDoctorsRegistrationFragmentToLogInFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

    }


}