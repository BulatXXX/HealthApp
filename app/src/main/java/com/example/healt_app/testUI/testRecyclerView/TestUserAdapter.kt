package com.example.healt_app.testUI.testRecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healt_app.R
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.TestUserCardBinding
import com.example.healt_app.doctor_appointment.doctorRecyclerView.DoctorAppointment

class TestUserAdapter(private val listener: Listener): RecyclerView.Adapter<TestUserAdapter.TestUserHolder>() {
    private var userList = ArrayList<User>()

    class TestUserHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(user: User,listener:Listener){
            val binding = TestUserCardBinding.bind(itemView)
            binding.idUser.text = user.id.toString()
            binding.name.text = user.login
            itemView.setOnClickListener {
                listener.onClick(user)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): TestUserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_user_card,parent,false)
        return TestUserHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: TestUserHolder , position: Int) {
        holder.bind(userList[position], listener)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<User>) {
        this.userList = list
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(user: User)
    }
}