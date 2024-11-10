package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicalRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: MedicalRecord): Long  // Retorna el ID del registro insertado

    @Update
    fun update(record: MedicalRecord): Int  // Retorna el número de filas actualizadas

    @Delete
    fun delete(record: MedicalRecord): Int  // Retorna el número de filas eliminadas

    @Query("SELECT * FROM medical_history WHERE userId = :userId")
    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>>

    @Query("SELECT * FROM medical_history")
    fun getAllMedicalRecords(): LiveData<List<MedicalRecord>>

    @Query("SELECT * FROM medical_history WHERE id = :recordId")
    fun getRecordById(recordId: Int): LiveData<MedicalRecord?>
}
