package com.angel.mi_armario

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 3500 // 3.5 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this@Splash, PantallaRegistroLogin::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }
}
