package com.example.healt_app.medicine.medicineRecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healt_app.R
import com.example.healt_app.databinding.MedicineItemBinding

class MedicineAdapter(private val listener: Listener) : RecyclerView.Adapter<MedicineAdapter.MedicineHolder>() {
    private var medicineList = ArrayList<Medicine>()

    class MedicineHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = MedicineItemBinding.bind(item)
        fun bind(medicine: Medicine, listener: Listener) = with(binding) {
            medicineName.text = medicine.name
            freq.text = medicine.frequency
            time.text = medicine.time
            itemView.setOnClickListener {
                listener.onClick(medicine)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): MedicineHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.medicine_item , parent , false)
        return MedicineHolder(view)
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    override fun onBindViewHolder(holder: MedicineHolder , position: Int) {
        holder.bind(medicineList[position],listener)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Medicine>) {
        this.medicineList = list
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(medicine: Medicine)
    }

}