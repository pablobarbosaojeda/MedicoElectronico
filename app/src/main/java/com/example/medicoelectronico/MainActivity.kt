package com.example.medicoelectronico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnAddRecord: Button
    private lateinit var btnViewAllRecords: Button
    private lateinit var btnLogout: Button
    private lateinit var btnSharePdf: Button
    private lateinit var btnSendToFHIR: Button
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
        medicalRecordViewModel =
            ViewModelProvider(this, factory)[MedicalRecordViewModel::class.java]

        // Observar registros del usuario específico
        medicalRecordViewModel.medicalRecords.observe(this) { records ->
            adapter.submitList(records)
        }

        // Inicializar los botones
        btnAddRecord = findViewById(R.id.btnAddRecord)
        btnViewAllRecords = findViewById(R.id.btnViewAllRecords)
        btnLogout = findViewById(R.id.btnLogout)
        btnSharePdf = findViewById(R.id.btnSharePdf)
        btnSendToFHIR = findViewById(R.id.btnSendToFHIR)

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

        // Botón para compartir registros en PDF
        btnSharePdf.setOnClickListener {
            medicalRecordViewModel.allMedicalRecords.observe(this) { records ->
                if (records.isNotEmpty()) {
                    val pdfFile = medicalRecordViewModel.createPdf(this, records.first())
                    shareFile(pdfFile)
                } else {
                    Toast.makeText(this, "No hay registros para compartir", Toast.LENGTH_SHORT)
                        .show()
                }
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

    // Método para compartir el archivo PDF
    private fun shareFile(file: File) {
        val uri = FileProvider.getUriForFile(
            this,
            "com.example.medicoelectronico.provider",  // Nombre del paquete con `.provider`
            file
        )
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(intent, "Compartir datos de salud"))
    }
}
