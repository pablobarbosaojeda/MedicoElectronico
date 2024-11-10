package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicalRecordViewModel(private val repository: MedicalRecordRepository) : ViewModel() {

    val medicalRecords: LiveData<List<MedicalRecord>> = repository.getMedicalRecords("user123")
    val allMedicalRecords: LiveData<List<MedicalRecord>> = repository.getAllMedicalRecords()

    fun insert(record: MedicalRecord) = viewModelScope.launch {
        repository.insert(record)
    }

    fun update(record: MedicalRecord) = viewModelScope.launch {
        repository.update(record)
    }

    fun delete(record: MedicalRecord) = viewModelScope.launch {
        repository.delete(record)
    }
}
