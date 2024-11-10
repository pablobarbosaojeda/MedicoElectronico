package com.example.medicoelectronico

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_history")
data class MedicalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val diagnosis: String,
    val treatment: String,
    val doctorName: String,
    val visitDate: String
)
