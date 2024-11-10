package com.example.medicoelectronico

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.File

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

    // Envía los datos del paciente al servidor FHIR
    fun sendPatientDataToFhirServer(record: MedicalRecord) = viewModelScope.launch {
        repository.sendPatientDataToFhirServer(record)
    }

    // Crea un archivo PDF con los datos del paciente
    fun createPdf(context: Context, record: MedicalRecord): File {
        return repository.createPdf(context, record)
    }
}
