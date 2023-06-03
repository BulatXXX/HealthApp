package com.example.healt_app.doctor_appointment.doctorRecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healt_app.R
import com.example.healt_app.databinding.DoctorsAppointmentItemBinding
import com.example.healt_app.medicine.medicineRecyclerView.Medicine

class DoctorAppointmentAdapter: RecyclerView.Adapter<DoctorAppointmentAdapter.DoctorAppointmentHolder>() {
    private var doctorAppointmentList = ArrayList<DoctorAppointment>()
    class DoctorAppointmentHolder(item: View): RecyclerView.ViewHolder(item) {
        fun bind(doctorAppointment: DoctorAppointment){
            val binding = DoctorsAppointmentItemBinding.bind(itemView)
            with(binding){
                doctorName.text = doctorAppointment.doctorName
                doctorOccupation.text=doctorAppointment.doctorOccupation
                time.text = doctorAppointment.time
                date.text = doctorAppointment.date

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): DoctorAppointmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctors_appointment_item,parent,false)
        return DoctorAppointmentHolder(view)
    }

    override fun getItemCount(): Int {
        return doctorAppointmentList.size
    }

    override fun onBindViewHolder(holder: DoctorAppointmentHolder , position: Int) {
        holder.bind(doctorAppointmentList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<DoctorAppointment>) {
        this.doctorAppointmentList = list
        notifyDataSetChanged()
    }
}


