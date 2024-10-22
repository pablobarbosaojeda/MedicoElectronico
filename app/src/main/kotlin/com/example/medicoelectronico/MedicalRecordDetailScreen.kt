package com.example.medicoelectronico

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MedicalRecordDetailScreen(recordId: String, viewModel: MedicalRecordViewModel, onSave: (MedicalRecord) -> Unit) {
    var record by remember { mutableStateOf(MedicalRecord()) }
    var doctorName by remember { mutableStateOf("") }
    var diagnosis by remember { mutableStateOf("") }
    var treatment by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    LaunchedEffect(recordId) {
        viewModel.getRecords(recordId) { fetchedRecords ->
            if (fetchedRecords.isNotEmpty()) {
                record = fetchedRecords.first()
                doctorName = record.doctorName
                diagnosis = record.diagnosis
                treatment = record.treatment
                date = record.date
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = doctorName,
            onValueChange = { doctorName = it },
            label = { Text("Doctor Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = diagnosis,
            onValueChange = { diagnosis = it },
            label = { Text("Diagnosis") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = treatment,
            onValueChange = { treatment = it },
            label = { Text("Treatment") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val updatedRecord = record.copy(
                    doctorName = doctorName,
                    diagnosis = diagnosis,
                    treatment = treatment,
                    date = date
                )
                onSave(updatedRecord)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}