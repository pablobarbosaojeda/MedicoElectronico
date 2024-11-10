package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicalRecordRepository(private val medicalRecordDao: MedicalRecordDao) {

    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>> {
        return medicalRecordDao.getMedicalRecords(userId)
    }

    fun getAllMedicalRecords(): LiveData<List<MedicalRecord>> {
        return medicalRecordDao.getAllMedicalRecords()
    }

    suspend fun insert(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.insert(record)
        }
    }

    suspend fun update(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.update(record)
        }
    }

    suspend fun delete(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.delete(record)
        }
    }

    fun getRecordById(recordId: Int): LiveData<MedicalRecord?> {
        return medicalRecordDao.getRecordById(recordId)
    }
}
