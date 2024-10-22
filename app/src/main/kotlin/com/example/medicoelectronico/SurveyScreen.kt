package com.example.medicoelectronico

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SurveyScreen(viewModel: SurveyViewModel, userId: String) {
    var surveys by remember { mutableStateOf(listOf<Survey>()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(userId) {
        viewModel.getSurveys(userId) { result ->
            result.onSuccess { fetchedSurveys ->
                surveys = fetchedSurveys
                isLoading = false
            }.onFailure { error ->
                errorMessage = error.message
                isLoading = false
            }
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
        }
    } else {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            surveys.forEach { survey ->
                Text(text = "Survey Title: ${survey.title}")
                Text(text = "Survey Description: ${survey.description}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}