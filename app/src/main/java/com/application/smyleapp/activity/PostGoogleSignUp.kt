package com.application.smyleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.application.smyleapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class PostGoogleSignUp : AppCompatActivity() {

    lateinit var edtname2 : EditText
    lateinit var edtphonenumber2 : EditText
    lateinit var btnSignUp2 : Button
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_google_sign_up)

        edtname2 = findViewById(R.id.edtname2)
        edtphonenumber2 = findViewById(R.id.edtphonenumber2)
        btnSignUp2 = findViewById(R.id.btnSignUp2)

        val email = intent.getStringExtra("account_email")
        val id = intent.getStringExtra("account_id")

        btnSignUp2.setOnClickListener {

            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(id)




            val hashMap:HashMap<String,String> = HashMap()
            hashMap.put("userId",id)
            hashMap.put("userName",edtname2.text.toString())
            hashMap.put("phoneNumber",edtphonenumber2.text.toString())
            hashMap.put("userEmail",email)

            databaseReference.setValue(hashMap).addOnCompleteListener(this){


                if (it.isSuccessful){


                    //open home activity
                    edtname2.setText("")

                    edtphonenumber2.setText("")


                    val intent = Intent(
                        this@PostGoogleSignUp,
                        MainActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(applicationContext,"saving to database failed ${it.result}", Toast.LENGTH_SHORT).show()
                    Log.d("ERROR","${it}")

                }
            }
        }

//        else{
//            Toast.makeText(applicationContext,"signup failed $it", Toast.LENGTH_SHORT).show()
//
//        }

        }





    }
