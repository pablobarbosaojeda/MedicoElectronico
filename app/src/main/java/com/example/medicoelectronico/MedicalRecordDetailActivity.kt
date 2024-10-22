package com.example.medicoelectronico

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MedicalRecordDetailActivity : ComponentActivity() {

    private lateinit var viewModel: MedicalRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MedicalRecordViewModel()

        val recordId = intent.getStringExtra("RECORD_ID") ?: return

        setContent {
            MedicoElectronicoTheme {
                MedicalRecordDetailScreen(
                    recordId = recordId,
                    viewModel = viewModel,
                    onSave = { record -> saveRecord(record) }
                )
            }
        }
    }

    private fun saveRecord(record: MedicalRecord) {
        lifecycleScope.launch {
            try {
                viewModel.updateRecord(record)
                Toast.makeText(this@MedicalRecordDetailActivity, "Record updated successfully.", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@MedicalRecordDetailActivity, "Failed to update record: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}