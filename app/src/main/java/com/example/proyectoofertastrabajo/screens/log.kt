//package com.example.proyectoofertastrabajo.screens
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.example.proyectoofertastrabajo.R
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//
//
//class log : AppCompatActivity() {
//
//
//    private lateinit var auth: FirebaseAuth
//    private lateinit var button: Button
//    private lateinit var textView: TextView
//    private lateinit var user: FirebaseUser
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        auth = FirebaseAuth.getInstance()
//        button = findViewById(R.id.salir)
//        textView = findViewById(R.id.PaginaPrincipal)
//        val user = auth.currentUser
//
//        if (user == null) {
//            val intent = Intent(applicationContext, Login::class.java)
//            startActivity(intent)
//            finish()
//        } else {
//            textView.text = user.email
//        }
//
//        button.setOnClickListener(){
//
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(applicationContext, Login::class.java)
//            startActivity(intent)
//            finish()
//
//        }
//
//    }//Fin onCreate
//}//FinMainActivity
