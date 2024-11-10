package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicalRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: MedicalRecord)

    @Query("SELECT * FROM medical_history WHERE userId = :userId")
    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>>

    @Update
    suspend fun update(record: MedicalRecord)
}
