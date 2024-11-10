package com.example.medicoelectronico

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "medical_history")
data class MedicalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val diagnosis: String,
    val treatment: String,
    val doctorName: String,
    val visitDate: String
) : Parcelable
