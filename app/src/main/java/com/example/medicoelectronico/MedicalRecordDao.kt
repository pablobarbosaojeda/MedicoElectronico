package com.example.medicoelectronico

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicalRecordDao {

    // Inserta un nuevo registro médico o lo reemplaza si ya existe (basado en el ID)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: MedicalRecord): Long  // Retorna el ID del registro insertado

    // Actualiza un registro médico existente en la base de datos
    @Update
    fun update(record: MedicalRecord): Int  // Retorna el número de filas actualizadas

    // Elimina un registro médico de la base de datos
    @Delete
    fun delete(record: MedicalRecord): Int  // Retorna el número de filas eliminadas

    // Obtiene los registros médicos de un usuario específico, observables con LiveData
    @Query("SELECT * FROM medical_history WHERE userId = :userId")
    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>>

    // Obtiene todos los registros médicos en la base de datos, observables con LiveData
    @Query("SELECT * FROM medical_history")
    fun getAllMedicalRecords(): LiveData<List<MedicalRecord>>

    // Obtiene un registro específico por su ID, observable con LiveData
    @Query("SELECT * FROM medical_history WHERE id = :recordId")
    fun getRecordById(recordId: Int): LiveData<MedicalRecord?>
}
