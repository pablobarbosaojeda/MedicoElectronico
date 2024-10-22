package com.example.medicoelectronico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SurveyViewModel(private val surveyRepository: SurveyRepository) : ViewModel() {

    private val _surveys = mutableStateOf<List<Survey>>(emptyList())
    val surveys: State<List<Survey>> get() = _surveys

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> get() = _errorMessage

    fun getSurveys(userId: String, onResult: (Result<List<Survey>>) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val surveys = surveyRepository.getSurveys(userId)
                _surveys.value = surveys
                onResult(Result.success(surveys))
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onResult(Result.failure(e))
            } finally {
                _isLoading.value = false
            }
        }
    }
}