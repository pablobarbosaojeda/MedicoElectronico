package com.example.medicoelectronico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnAddRecord: Button
    private lateinit var btnViewAllRecords: Button
    private lateinit var btnLogout: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var medicalRecordViewModel: MedicalRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Verificar si el usuario está autenticado
        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MedicalRecordAdapter { record ->
            // Abre AddRecordActivity en modo de edición con el registro seleccionado
            val intent = Intent(this, AddRecordActivity::class.java)
            intent.putExtra("record", record)  // Pasa el registro al intent
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Inicializar el ViewModel con la fábrica
        val medicalRecordDao = AppDatabase.getDatabase(application).medicalRecordDao()
        val repository = MedicalRecordRepository(medicalRecordDao)
        val factory = MedicalRecordViewModelFactory(repository)
        medicalRecordViewModel = ViewModelProvider(this, factory)[MedicalRecordViewModel::class.java]

        // Observar registros del usuario específico
        medicalRecordViewModel.medicalRecords.observe(this) { records ->
            adapter.submitList(records)
        }

        // Inicializar los botones
        btnAddRecord = findViewById(R.id.btnAddRecord)
        btnViewAllRecords = findViewById(R.id.btnViewAllRecords)
        btnLogout = findViewById(R.id.btnLogout)

        // Botón para agregar un nuevo registro
        btnAddRecord.setOnClickListener {
            val intent = Intent(this, AddRecordActivity::class.java)
            startActivity(intent)
        }

        // Botón para ver todos los registros
        btnViewAllRecords.setOnClickListener {
            medicalRecordViewModel.allMedicalRecords.observe(this) { records ->
                adapter.submitList(records)
            }
        }

        // Configurar el botón de cerrar sesión
        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
