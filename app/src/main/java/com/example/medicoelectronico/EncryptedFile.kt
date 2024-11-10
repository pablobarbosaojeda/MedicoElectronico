package com.example.medicoelectronico

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File

class EncryptionUtils(private val context: Context) {
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    fun encryptData(fileName: String, data: String) {
        val encryptedFile = EncryptedFile.Builder(
            File(context.filesDir, fileName),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        encryptedFile.openFileOutput().use { outputStream ->
            outputStream.write(data.toByteArray())
        }
    }

    fun decryptData(fileName: String): String {
        val encryptedFile = EncryptedFile.Builder(
            File(context.filesDir, fileName),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        return encryptedFile.openFileInput().bufferedReader().useLines { lines ->
            lines.fold("") { acc, line -> "$acc\n$line" }
        }
    }
}
