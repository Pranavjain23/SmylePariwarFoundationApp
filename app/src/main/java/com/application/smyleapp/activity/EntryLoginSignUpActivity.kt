package com.application.smyleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.application.smyleapp.R

class EntryLoginSignUpActivity : AppCompatActivity() {

    lateinit var btnLogin : Button
    lateinit var btnSignUp : Button
    lateinit var loginWithGoogle : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_login_sign_up)

        btnLogin= findViewById(R.id.btnLogin)
        btnSignUp= findViewById(R.id.btnSignUp)
        loginWithGoogle= findViewById(R.id.loginWithGoogle)

        btnLogin.setOnClickListener {
            val intent = Intent(this@EntryLoginSignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val intent = Intent(this@EntryLoginSignUpActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginWithGoogle.setOnClickListener {
        }
    }
}