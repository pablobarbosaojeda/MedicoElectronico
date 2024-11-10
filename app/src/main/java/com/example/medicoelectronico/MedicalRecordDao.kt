package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicalRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: MedicalRecord): Long // Cambiar el valor de retorno a Long

    @Query("SELECT * FROM medical_history WHERE userId = :userId")
    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>>

    @Update
    fun update(record: MedicalRecord): Int // Cambiar el valor de retorno a Int
}
