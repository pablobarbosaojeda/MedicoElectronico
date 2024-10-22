package com.example.medicoelectronico

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