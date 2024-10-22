package com.example.medicoelectronico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicalRecordViewModel : ViewModel() {

    private val repository = MedicalRecordRepository()

    fun addRecord(record: MedicalRecord) {
        viewModelScope.launch {
            repository.addRecord(record)
        }
    }

    fun getRecords(userId: String, onResult: (List<MedicalRecord>) -> Unit) {
        viewModelScope.launch {
            val records = repository.getRecords(userId)
            onResult(records)
        }
    }

    fun updateRecord(record: MedicalRecord) {
        viewModelScope.launch {
            repository.updateRecord(record)
        }
    }

    fun deleteRecord(recordId: String) {
        viewModelScope.launch {
            repository.deleteRecord(recordId)
        }
    }
}