package com.angel.mi_armario
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.google.firebase.auth.FirebaseAuth

class PantallaPrincipal : AppCompatActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var logoutButton: Button
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        counterTextView = findViewById(R.id.counterTextView)
        incrementButton = findViewById(R.id.incrementButton)
        logoutButton = findViewById(R.id.logoutButton)

        incrementButton.setOnClickListener {
            incrementCounter()
        }

        logoutButton.setOnClickListener {
            signOut()
        }
    }

    private fun incrementCounter() {
        counter++
        counterTextView.text = counter.toString()
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, PantallaRegistroLogin::class.java))
    }
}
