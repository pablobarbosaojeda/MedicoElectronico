package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicalRecordViewModel(private val repository: MedicalRecordRepository) : ViewModel() {

    // Observa los registros médicos de un usuario específico
    val medicalRecords: LiveData<List<MedicalRecord>> = repository.getMedicalRecords("user123")

    // Observa todos los registros médicos
    val allMedicalRecords: LiveData<List<MedicalRecord>> = repository.getAllMedicalRecords()

    // Inserta un nuevo registro médico
    fun insert(record: MedicalRecord) = viewModelScope.launch {
        repository.insert(record)
    }

    // Actualiza un registro médico existente
    fun update(record: MedicalRecord) = viewModelScope.launch {
        repository.update(record)
    }

    // Elimina un registro médico
    fun delete(record: MedicalRecord) = viewModelScope.launch {
        repository.delete(record)
    }
}
