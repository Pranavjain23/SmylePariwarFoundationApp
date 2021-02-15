package com.application.smyleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.application.smyleapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_forgot_password.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    lateinit var btnContinue : Button
    lateinit var signUp : TextView
    lateinit var forgotPassword : TextView
//    lateinit var forgotPasswordUsername : EditText
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnContinue = findViewById(R.id.btnContinue)
        forgotPassword = findViewById(R.id.forgotPassword)



        signUp = findViewById(R.id.signUp)
//        btnContinue.setOnClickListener {
//            val intent = Intent(this@LoginActivity , MainActivity::class.java)
//            startActivity(intent)
//        }

        //        firebaseUser = auth!!.currentUser!! //if he never logged out

        //check if user login then navigate to user screen
        auth = FirebaseAuth.getInstance()
        forgotPassword.setOnClickListener {
            val builder  =  AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")

            val view = layoutInflater.inflate(R.layout.dialog_forgot_password,null)
            val username = view.findViewById<EditText>(R.id.forgotPasswordUsername)
            builder.setView(view)
            builder.setPositiveButton("Reset", { _, _ -> forgotPassword(username)})
            builder.setNegativeButton("close", { _, _ ->})
            builder.show()


        }












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
                                MainActivity::class.java
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
    private fun forgotPassword(username : EditText){
        if(username.text.toString().isEmpty()){
            return
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return

        }else{
            auth?.sendPasswordResetEmail(username.text.toString())?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this@LoginActivity,"Email Sent",Toast.LENGTH_LONG).show()
                }
            }
        }

    }


}