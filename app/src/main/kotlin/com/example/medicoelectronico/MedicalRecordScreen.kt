package com.example.medicoelectronico

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MedicalRecordScreen(viewModel: MedicalRecordViewModel, userId: String) {
    val records by remember { mutableStateOf(listOf<MedicalRecord>()) }

    LaunchedEffect(userId) {
        viewModel.getRecords(userId) { fetchedRecords ->
            records = fetchedRecords
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        records.forEach { record ->
            Text(text = "Doctor: ${record.doctorName}")
            Text(text = "Diagnosis: ${record.diagnosis}")
            Text(text = "Treatment: ${record.treatment}")
            Text(text = "Date: ${record.date}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}