package com.example.healt_app.testUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healt_app.R
import com.example.healt_app.dataBase.MainDB
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.FragmentTestBinding
import com.example.healt_app.testUI.testRecyclerView.TestUserAdapter


class TestFragment : Fragment() , TestUserAdapter.Listener {

    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!
    private val users = ArrayList<User>()
    private val adapter = TestUserAdapter(this)
    private var selectedUser: User? = null

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTestBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        val db = MainDB.getDb(requireContext())

        db.getDao().getUsers().asLiveData().observe(requireActivity()) { list ->
            users.clear()
            list.forEach {
                users.add(it)
            }
            adapter.updateList(users)
            binding.rv.adapter = adapter
        }

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
        binding.saveBtn.setOnClickListener {
         //   val user = User(null , binding.nameEt.text.toString() , "00.00.00")
          //  Thread { db.getDao().insertUser(user) }.start()

        }
        binding.deleteBtn.setOnClickListener {
            Thread { selectedUser?.id?.let { user_id -> db.getDao().deleteUserById(user_id) } }.start()
        }


    }



    override fun onClick(user: User) {
        selectedUser = user
        binding.userInfo.text = user.toString()

    }


}