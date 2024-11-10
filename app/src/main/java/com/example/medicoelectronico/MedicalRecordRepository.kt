package com.example.medicoelectronico

import androidx.lifecycle.LiveData

class MedicalRecordRepository(private val medicalRecordDao: MedicalRecordDao) {
    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>> {
        return medicalRecordDao.getMedicalRecords(userId)
    }

    suspend fun insert(record: MedicalRecord) {
        medicalRecordDao.insert(record)
    }
}
