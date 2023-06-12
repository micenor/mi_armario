package com.angel.mi_armario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class PantallaRegistrarse : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etContraseña: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etEmail: EditText

    private lateinit var btnRegistrar: Button

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registrarse)


        // Inicializar Firebase Auth y Database
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // Obtener referencias a los elementos de la interfaz de usuario
        etNombre = findViewById(R.id.tvNombre)
        etContraseña = findViewById(R.id.tvPasswRegistro)
        etApellidos = findViewById(R.id.tvApellidos)
        etEmail = findViewById(R.id.tvEmailRegistro)

        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val nombre = etNombre.text.toString()
        val contraseña = etContraseña.text.toString()
        val apellidos = etApellidos.text.toString()
        val email = etEmail.text.toString()

        // Crear el usuario en Firebase Auth
        firebaseAuth.createUserWithEmailAndPassword(email, contraseña)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Usuario registrado exitosamente
                    val userId = firebaseAuth.currentUser?.uid

                    // Guardar los datos en la base de datos de Firebase
                    val usuariosRef = database.getReference("users")
                    userId?.let {
                        val usuario = Usuario(nombre, apellidos, email)
                        val usuarioData = HashMap<String, Any>()
                        usuarioData["nombre"] = usuario.nombre
                        usuarioData["apellidos"] = usuario.apellidos
                        usuarioData["email"] = usuario.email

                        usuariosRef.child(it).setValue(usuarioData)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Registro exitoso
                                    // Puedes redirigir a otra pantalla, mostrar un mensaje de éxito, etc.
                                } else {
                                    // Registro fallido
                                    val error = task.exception?.message
                                    Log.e("Firebase", "Error al guardar los datos: $error")
                                    Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }

                    // Navegar a la pantalla de inicio de sesión
                    val intent = Intent(this, PantallaRegistroLogin::class.java)
                    startActivity(intent)
                    finish() // Opcional, para cerrar esta actividad y evitar volver atrás
                } else {
                    // Error en el registro
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
