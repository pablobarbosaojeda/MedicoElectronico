package com.example.medicoelectronico

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddRecordActivity : AppCompatActivity() {

    private lateinit var edtDiagnosis: EditText
    private lateinit var edtTreatment: EditText
    private lateinit var edtDoctorName: EditText
    private lateinit var edtVisitDate: EditText
    private lateinit var btnSave: Button

    // Inicializar el ViewModel para insertar o actualizar el registro
    private val medicalRecordViewModel: MedicalRecordViewModel by viewModels {
        val medicalRecordDao = AppDatabase.getDatabase(application).medicalRecordDao()
        val repository = MedicalRecordRepository(medicalRecordDao)
        MedicalRecordViewModelFactory(repository)
    }

    private var currentRecord: MedicalRecord? = null  // Variable para almacenar el registro actual en modo edición

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        // Inicializar las vistas
        edtDiagnosis = findViewById(R.id.edtDiagnosis)
        edtTreatment = findViewById(R.id.edtTreatment)
        edtDoctorName = findViewById(R.id.edtDoctorName)
        edtVisitDate = findViewById(R.id.edtVisitDate)
        btnSave = findViewById(R.id.btnSave)

        // Verificar si se está editando un registro existente
        currentRecord = intent.getParcelableExtra("record")
        currentRecord?.let { record ->
            edtDiagnosis.setText(record.diagnosis)
            edtTreatment.setText(record.treatment)
            edtDoctorName.setText(record.doctorName)
            edtVisitDate.setText(record.visitDate)
            btnSave.text = "Actualizar Registro"
        }

        // Configurar el botón de guardar/actualizar
        btnSave.setOnClickListener {
            val diagnosis = edtDiagnosis.text.toString()
            val treatment = edtTreatment.text.toString()
            val doctorName = edtDoctorName.text.toString()
            val visitDate = edtVisitDate.text.toString()

            if (diagnosis.isNotEmpty() && doctorName.isNotEmpty() && visitDate.isNotEmpty()) {
                // Crear o actualizar el registro según el modo (inserción o edición)
                val record = currentRecord?.copy(
                    diagnosis = diagnosis,
                    treatment = treatment,
                    doctorName = doctorName,
                    visitDate = visitDate
                ) ?: MedicalRecord(
                    userId = "user123",  // Puedes hacerlo dinámico según tu implementación
                    diagnosis = diagnosis,
                    treatment = treatment,
                    doctorName = doctorName,
                    visitDate = visitDate
                )

                if (currentRecord == null) {
                    // Insertar un nuevo registro
                    medicalRecordViewModel.insert(record)
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show()
                } else {
                    // Actualizar el registro existente
                    medicalRecordViewModel.update(record)
                    Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
                }

                finish() // Cerrar la actividad después de guardar o actualizar
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
