package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicalRecordRepository(private val medicalRecordDao: MedicalRecordDao) {

    // Obtiene los registros médicos de un usuario específico
    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>> {
        return medicalRecordDao.getMedicalRecords(userId)
    }

    // Obtiene todos los registros médicos
    fun getAllMedicalRecords(): LiveData<List<MedicalRecord>> {
        return medicalRecordDao.getAllMedicalRecords()
    }

    // Inserta un nuevo registro médico en un hilo de fondo
    suspend fun insert(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.insert(record)
        }
    }

    // Actualiza un registro médico existente en un hilo de fondo
    suspend fun update(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.update(record)
        }
    }

    // Elimina un registro médico en un hilo de fondo
    suspend fun delete(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.delete(record)
        }
    }

    // Obtiene un registro específico por ID
    fun getRecordById(recordId: Int): LiveData<MedicalRecord?> {
        return medicalRecordDao.getRecordById(recordId)
    }
}
