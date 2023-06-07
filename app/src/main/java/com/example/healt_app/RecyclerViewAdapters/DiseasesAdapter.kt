package com.example.healt_app.RecyclerViewAdapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healt_app.R
import com.example.healt_app.dataBase.Disease
import com.example.healt_app.dataBase.User
import com.example.healt_app.databinding.DiseaseItemBinding

class DiseasesAdapter(private val listener: Listener): RecyclerView.Adapter<DiseasesAdapter.DiseasesHolder>() {
    private var diseasesList = ArrayList<Disease>()
    class DiseasesHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DiseaseItemBinding.bind(view)
        fun bind(disease: Disease,listener: Listener){
            binding.diseaseName.text = disease.name
            binding.description.text = disease.description
            binding.doctorName.text = disease.doctorName
            itemView.setOnClickListener {
                listener.onClick(disease)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): DiseasesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disease_item,parent,false)
        return DiseasesHolder(view)
    }

    override fun getItemCount(): Int {
        return diseasesList.size
    }

    override fun onBindViewHolder(holder: DiseasesHolder , position: Int) {
       holder.bind(diseasesList[position],listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Disease>){
        this.diseasesList = list
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(disease: Disease)
    }
}