package com.example.medicoelectronico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicoelectronico.MedicalRecordViewModel
import com.example.medicoelectronico.MedicalRecordAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var btnAddRecord: Button
    private lateinit var recyclerView: RecyclerView
    private val medicalRecordViewModel: MedicalRecordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        btnAddRecord = findViewById(R.id.btnAddRecord)
        recyclerView = findViewById(R.id.recyclerView)

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MedicalRecordAdapter()
        recyclerView.adapter = adapter

        // Observar los registros médicos desde el ViewModel
        medicalRecordViewModel.medicalRecords.observe(this) { records ->
            adapter.submitList(records)
        }

        // Configurar el botón para agregar un nuevo registro
        btnAddRecord.setOnClickListener {
            // Iniciar la actividad de agregar nuevo registro
            startActivity(Intent(this, AddRecordActivity::class.java))
        }
    }
}
