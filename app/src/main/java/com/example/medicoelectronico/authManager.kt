package com.example.medicoelectronico

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class authManager(private val context: Context) {

    fun registrarUsuario(username: String, password: String, onComplete: (Boolean, String?) -> Unit){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)
                } else {
                    val msjError = when (task.exception){
                        is FirebaseAuthInvalidCredentialsException -> "Credenciales incorrectas."
                        is FirebaseAuthInvalidCredentialsException -> "Usuario no registrado"
                        else -> "Error 404" //random
                    }
                    onComplete(false, task.exception?.message)
                }
            }
    }
    fun resetContra(email: String, onComplete: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, "Se ha enviado un correo para restablecer tu contraseña")
                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }
    fun verificacionMail(onComplete: (Boolean, String?) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onComplete(true, "Correo de verificación enviado")
            } else {
                onComplete(false, task.exception?.message)
            }
        }
    }
    fun isEmailVerified(): Boolean {
        val user = FirebaseAuth.getInstance().currentUser
        return user?.isEmailVerified ?: false
    }
    fun login(username: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)
                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }

    fun isLoggedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}