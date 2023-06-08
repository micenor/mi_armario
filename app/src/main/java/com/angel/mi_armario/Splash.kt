package com.angel.mi_armario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import java.util.logging.Handler

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        android.os.Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
            finish()
        }, 3500)

    }
}