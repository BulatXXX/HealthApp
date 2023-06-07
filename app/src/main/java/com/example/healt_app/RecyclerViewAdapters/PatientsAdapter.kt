package com.example.healt_app.RecyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healt_app.R
import com.example.healt_app.dataBase.Medicine
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.PatientItemBinding
import java.time.LocalDate
import java.time.Period

class PatientsAdapter(private val listener : Listener) : RecyclerView.Adapter<PatientsAdapter.PatientHolder>() {
    var patientsList = ArrayList<User>()
    class PatientHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = PatientItemBinding.bind(view)
        @SuppressLint("SetTextI18n")
        fun bind(user: User , listener: Listener){
            binding.patientId.text = "#${user.id.toString()}"
            binding.patientName.text = user.name
            binding.ageValue.text = countAge(user.birthDate).toString()
            itemView.setOnClickListener {
                listener.onClick(user)
            }
        }
        private fun countAge(birthDate: String): Int {
            val currentDate = LocalDate.now()
            val birth_date = LocalDate.of(
                birthDate.substring(6).toInt() ,
                birthDate.substring(3 , 5).toInt() ,
                birthDate.substring(0 , 2).toInt()
            )
            return Period.between(birth_date , currentDate).years
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int
    ): PatientHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.patient_item,parent,false)
        return PatientHolder(view)
    }

    override fun onBindViewHolder(holder: PatientHolder , position: Int) {
        holder.bind(patientsList[position],listener)
    }

    override fun getItemCount(): Int {
        return patientsList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<User>) {
        this.patientsList = list
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(user: User)
    }


}