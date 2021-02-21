package com.application.smyleapp.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.smyleapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    lateinit var btnSignUp : Button
    lateinit var databaseReference: DatabaseReference
    lateinit var signup_back : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        btnSignUp = findViewById(R.id.btnSignUp)
        signup_back = findViewById(R.id.signup_back)

            signup_back.setOnClickListener {
            val intent = Intent(
                this@SignUpActivity,
                EntryLoginSignUpActivity::class.java
            )
            startActivity(intent)
            finish()
        }
        btnSignUp.setOnClickListener {
            val userName = edtname.text.toString()

            val email = edtemail.text.toString()
            val phoneNumber = edtphonenumber.text.toString()
            val password = edtpass.text.toString()
            val confirmPassword = edtconfirmpass.text.toString()

            if (TextUtils.isEmpty(userName)){
                Toast.makeText(applicationContext,"username is required", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"email is required", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(applicationContext,"phone number is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"password is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(applicationContext,"confirm password is required", Toast.LENGTH_SHORT).show()
            }

            if (!password.equals(confirmPassword)){
                Toast.makeText(applicationContext,"passwords do not match", Toast.LENGTH_SHORT).show()
            }
            if(password.equals(confirmPassword) && isValidPassword(password)){
                registerUser(userName,email,password,phoneNumber)
            }else{
                Toast.makeText(applicationContext,"Please enter a valid password", Toast.LENGTH_SHORT).show()

            }



        }



    }

    private fun registerUser(userName:String,email:String,password:String,phoneNumber:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful) {



                    val user: FirebaseUser? = auth.currentUser



                    val userId:String = user!!.uid
                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)




                    val hashMap:HashMap<String,String> = HashMap()
                    hashMap.put("userId",userId)
                    hashMap.put("userName",userName)
                    hashMap.put("phoneNumber",phoneNumber)
                    hashMap.put("userEmail",email)

                    databaseReference.setValue(hashMap).addOnCompleteListener(this){


                        if (it.isSuccessful){


                            //open home activity
                            edtname.setText("")
                            edtemail.setText("")
                            edtphonenumber.setText("")
                            edtpass.setText("")
                            edtconfirmpass.setText("")

                    val intent = Intent(
                        this@SignUpActivity,
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

                else{
                    Toast.makeText(applicationContext,"signup failed $it", Toast.LENGTH_SHORT).show()

                }
            }

    }
    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
}