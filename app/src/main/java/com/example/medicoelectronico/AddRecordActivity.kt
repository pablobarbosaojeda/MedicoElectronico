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

    private val medicalRecordViewModel: MedicalRecordViewModel by viewModels {
        val medicalRecordDao = AppDatabase.getDatabase(application).medicalRecordDao()
        val repository = MedicalRecordRepository(medicalRecordDao)
        MedicalRecordViewModelFactory(repository)
    }

    private var currentRecord: MedicalRecord? = null  // Variable para almacenar el registro en edición

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        // Inicializar las vistas
        edtDiagnosis = findViewById(R.id.edtDiagnosis)
        edtTreatment = findViewById(R.id.edtTreatment)
        edtDoctorName = findViewById(R.id.edtDoctorName)
        edtVisitDate = findViewById(R.id.edtVisitDate)
        btnSave = findViewById(R.id.btnSave)

        // Verificar si se recibe un registro para edición
        currentRecord = intent.getParcelableExtra("record")
        currentRecord?.let { record ->
            edtDiagnosis.setText(record.diagnosis)
            edtTreatment.setText(record.treatment)
            edtDoctorName.setText(record.doctorName)
            edtVisitDate.setText(record.visitDate)
            btnSave.text = "Actualizar Registro"
        }

        // Configurar el botón de guardar o actualizar
        btnSave.setOnClickListener {
            val diagnosis = edtDiagnosis.text.toString()
            val treatment = edtTreatment.text.toString()
            val doctorName = edtDoctorName.text.toString()
            val visitDate = edtVisitDate.text.toString()

            if (diagnosis.isNotEmpty() && doctorName.isNotEmpty() && visitDate.isNotEmpty()) {
                val record = currentRecord?.copy(
                    diagnosis = diagnosis,
                    treatment = treatment,
                    doctorName = doctorName,
                    visitDate = visitDate
                ) ?: MedicalRecord(
                    userId = "user123",
                    diagnosis = diagnosis,
                    treatment = treatment,
                    doctorName = doctorName,
                    visitDate = visitDate
                )

                if (currentRecord == null) {
                    medicalRecordViewModel.insert(record)
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show()
                } else {
                    medicalRecordViewModel.update(record)
                    Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
                }

                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
