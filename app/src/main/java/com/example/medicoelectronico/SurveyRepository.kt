package com.example.medicoelectronico

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SurveyRepository(private val surveyDao: SurveyDao) {

    suspend fun addSurvey(survey: Survey) {
        withContext(Dispatchers.IO) {
            try {
                surveyDao.insert(survey)
            } catch (e: Exception) {
                // Handle the error appropriately
                throw e
            }
        }
    }

    suspend fun getSurveys(userId: String): List<Survey> {
        return withContext(Dispatchers.IO) {
            try {
                surveyDao.getSurveysByUserId(userId)
            } catch (e: Exception) {
                // Handle the error appropriately
                emptyList()
            }
        }
    }

    suspend fun updateSurvey(survey: Survey) {
        withContext(Dispatchers.IO) {
            try {
                surveyDao.update(survey)
            } catch (e: Exception) {
                // Handle the error appropriately
                throw e
            }
        }
    }

    suspend fun deleteSurvey(surveyId: String) {
        withContext(Dispatchers.IO) {
            try {
                surveyDao.deleteById(surveyId)
            } catch (e: Exception) {
                // Handle the error appropriately
                throw e
            }
        }
    }
}