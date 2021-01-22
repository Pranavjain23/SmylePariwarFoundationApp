package com.application.smyleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.application.smyleapp.R

class LoginActivity : AppCompatActivity() {
    lateinit var btnContinue : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnContinue = findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            val intent = Intent(this@LoginActivity , MainActivity::class.java)
            startActivity(intent)
        }
    }
}