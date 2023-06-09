package com.angel.mi_armario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class PantallaPrincipal : AppCompatActivity() {

    private  lateinit var  firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        firebaseAuth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        findViewById<TextView>(R.id.textView4).text = email + "\n" + displayName

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, PantallaRegistroLogin::class.java))
        }
    }

}