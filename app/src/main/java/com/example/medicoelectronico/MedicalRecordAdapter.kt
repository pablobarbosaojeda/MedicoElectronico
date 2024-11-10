package com.example.medicoelectronico

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medicoelectronico.MedicalRecord

class MedicalRecordAdapter :
    ListAdapter<MedicalRecord, MedicalRecordAdapter.MedicalRecordViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalRecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medical_record, parent, false)
        return MedicalRecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicalRecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MedicalRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDiagnosis: TextView = itemView.findViewById(R.id.txtDiagnosis)
        private val txtDoctor: TextView = itemView.findViewById(R.id.txtDoctor)
        private val txtDate: TextView = itemView.findViewById(R.id.txtDate)

        fun bind(record: MedicalRecord) {
            txtDiagnosis.text = record.diagnosis
            txtDoctor.text = record.doctorName
            txtDate.text = record.visitDate
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MedicalRecord>() {
            override fun areItemsTheSame(oldItem: MedicalRecord, newItem: MedicalRecord): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MedicalRecord, newItem: MedicalRecord): Boolean {
                return oldItem == newItem
            }
        }
    }
}
