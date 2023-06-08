package com.angel.mi_armario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PantallaRegistroLogin : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var  authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro_login)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val tvEmail : TextView = findViewById(R.id.tvEmail)
        val tvPassw : TextView = findViewById(R.id.tvPassword)

            firebaseAuth = Firebase.auth
            btnLogin.setOnClickListener() {
                signIn(tvEmail.text.toString(), tvPassw.text.toString())
            }

    }


    private fun signIn(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    //intent a la pantalla principal

                } else {
                    Toast.makeText(baseContext, "Error en el registro", Toast.LENGTH_SHORT).show()
                }
            }
    }
}