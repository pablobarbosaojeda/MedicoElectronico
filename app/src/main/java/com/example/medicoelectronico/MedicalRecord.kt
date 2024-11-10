package com.example.medicoelectronico

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_history")
data class MedicalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,   // ID de la entidad, debe ser un tipo primitivo o String
    val userId: String,                                 // Solo tipos compatibles con Room (Int, String, etc.)
    val diagnosis: String,
    val treatment: String,
    val doctorName: String,
    val visitDate: String
)
