package com.example.medicoelectronico

import android.content.Context
import androidx.lifecycle.LiveData
import ca.uhn.fhir.context.FhirContext
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.hl7.fhir.r4.model.Enumerations
import org.hl7.fhir.r4.model.Patient
import java.io.File
import java.text.SimpleDateFormat

class MedicalRecordRepository(private val medicalRecordDao: MedicalRecordDao) {

    // Obtiene los registros médicos de un usuario específico
    fun getMedicalRecords(userId: String): LiveData<List<MedicalRecord>> {
        return medicalRecordDao.getMedicalRecords(userId)
    }

    // Obtiene todos los registros médicos
    fun getAllMedicalRecords(): LiveData<List<MedicalRecord>> {
        return medicalRecordDao.getAllMedicalRecords()
    }

    // Inserta un nuevo registro médico en un hilo de fondo
    suspend fun insert(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.insert(record)
        }
    }

    // Actualiza un registro médico existente en un hilo de fondo
    suspend fun update(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.update(record)
        }
    }

    // Elimina un registro médico en un hilo de fondo
    suspend fun delete(record: MedicalRecord) {
        withContext(Dispatchers.IO) {
            medicalRecordDao.delete(record)
        }
    }

    // Obtiene un registro específico por ID
    fun getRecordById(recordId: Int): LiveData<MedicalRecord?> {
        return medicalRecordDao.getRecordById(recordId)
    }

    // Envía los datos del paciente a un servidor FHIR
    suspend fun sendPatientDataToFhirServer(patientData: MedicalRecord) {
        withContext(Dispatchers.IO) {
            val fhirContext = FhirContext.forR4()
            val client = fhirContext.newRestfulGenericClient("https://your-fhir-server.com/fhir")

            val patient = Patient().apply {
                addName().setFamily(patientData.doctorName).addGiven(patientData.userId)
                gender = Enumerations.AdministrativeGender.MALE
                birthDate = SimpleDateFormat("yyyy-MM-dd").parse(patientData.visitDate)
            }

            client.create().resource(patient).execute()
        }
    }

    // Crea un archivo PDF con los datos del paciente
    fun createPdf(context: Context, patientData: MedicalRecord): File {
        val pdfFile = File(context.getExternalFilesDir(null), "patient_data.pdf")
        val pdfWriter = PdfWriter(pdfFile)
        val pdfDocument = com.itextpdf.kernel.pdf.PdfDocument(pdfWriter)
        val document = Document(pdfDocument)

        document.add(Paragraph("Datos del Paciente"))
        document.add(Paragraph("Nombre: ${patientData.userId}"))
        document.add(Paragraph("Diagnóstico: ${patientData.diagnosis}"))
        document.add(Paragraph("Tratamiento: ${patientData.treatment}"))
        document.add(Paragraph("Médico: ${patientData.doctorName}"))
        document.add(Paragraph("Fecha de visita: ${patientData.visitDate}"))

        document.close()
        return pdfFile
    }
}
