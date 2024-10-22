package com.example.medicoelectronico

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MedicalRecordRepository {

    private val db = FirebaseFirestore.getInstance()
    private val recordsCollection = db.collection("medical_records")

    suspend fun addRecord(record: MedicalRecord) {
        recordsCollection.add(record).await()
    }

    suspend fun getRecords(userId: String): List<MedicalRecord> {
        return recordsCollection.whereEqualTo("userId", userId).get().await().toObjects(MedicalRecord::class.java)
    }

    suspend fun updateRecord(record: MedicalRecord) {
        recordsCollection.document(record.id).set(record).await()
    }

    suspend fun deleteRecord(recordId: String) {
        recordsCollection.document(recordId).delete().await()
    }
}