package com.example.healt_app.RecyclerViewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.healt_app.R
import com.example.healt_app.dataBase.Appointment
import com.example.healt_app.databinding.DoctorsAppointmentItemBinding

class AppointmentAdapter(private val listener: Listener,private val mode: Boolean): RecyclerView.Adapter<AppointmentAdapter.AppointmentHolder>() {
    private var appointmentsList = ArrayList<Appointment>()
    class AppointmentHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DoctorsAppointmentItemBinding.bind(view)
        fun bind(appointment: Appointment,listener: Listener,mode: Boolean){
            with(binding){
                //mode==true for doctors, false for patients
                date.text = appointment.date
                time.text = appointment.time
                if(mode){
                    doctorOccupation.text = appointment.patientName
                    doctorName.isVisible = false
                    itemView.setOnClickListener {
                        listener.onClick(appointment)
                    }
                }else{
                    doctorOccupation.text = appointment.post
                    doctorName.text = appointment.doctorName
                    doctorName.isVisible = true

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): AppointmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctors_appointment_item,parent,false)
        return AppointmentHolder(view)
    }

    override fun getItemCount(): Int {
        return appointmentsList.size
    }

    override fun onBindViewHolder(holder: AppointmentHolder , position: Int) {
       holder.bind(appointmentsList[position],listener,mode)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Appointment>) {
        this.appointmentsList = list
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(appointment: Appointment)
    }

}