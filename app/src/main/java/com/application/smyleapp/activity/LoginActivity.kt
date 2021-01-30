package com.application.smyleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.application.smyleapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var btnContinue : Button
    lateinit var signUp : TextView
    private var auth: FirebaseAuth? = null
    private  var firebaseUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnContinue = findViewById(R.id.btnContinue)
        signUp = findViewById(R.id.signUp)
//        btnContinue.setOnClickListener {
//            val intent = Intent(this@LoginActivity , MainActivity::class.java)
//            startActivity(intent)
//        }



        auth = FirebaseAuth.getInstance()
//        firebaseUser = auth!!.currentUser!! //if he never logged out

        //check if user login then navigate to user screen
//        if (firebaseUser != null) {
//            val intent = Intent(
//                this@LoginActivity,
//                MainActivity::class.java
//            )
//            startActivity(intent)
//            finish()
//        }

        btnContinue.setOnClickListener {
            val email = mail_et.text.toString()
            val password = password_et.text.toString()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(
                    applicationContext,
                    "email and password are required",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            mail_et.setText("")
                            password_et.setText("")
                            val intent = Intent(
                                this@LoginActivity,
                                SendNotif::class.java
                            )
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "email or password invalid",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

       signUp.setOnClickListener {
            val intent = Intent(
                this@LoginActivity,
                SignUpActivity::class.java
            )
            startActivity(intent)

        }
    }
}