package com.application.smyleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.application.smyleapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

//class EntryLoginSignUpActivity : AppCompatActivity() {

//    lateinit var btnLogin : Button
//    lateinit var btnSignUp : Button
//    lateinit var loginWithGoogle : ImageButton
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_entry_login_sign_up)
//
//        btnLogin= findViewById(R.id.btnLogin)
//        btnSignUp= findViewById(R.id.btnSignUp)
//        loginWithGoogle= findViewById(R.id.loginWithGoogle)
//
//        btnLogin.setOnClickListener {
//            val intent = Intent(this@EntryLoginSignUpActivity, LoginActivity::class.java)
//            startActivity(intent)
//        }
//
//        btnSignUp.setOnClickListener {
//            val intent = Intent(this@EntryLoginSignUpActivity, SignUpActivity::class.java)
//            startActivity(intent)
//        }
//
//        loginWithGoogle.setOnClickListener {
//        }
//    }





//}



class EntryLoginSignUpActivity : AppCompatActivity() {
    lateinit var btnLogin : Button
    lateinit var btnSignUp: Button
    lateinit var loginWithGoogle: ImageButton
    // lateinit var loginWithPhone: ImageButton

    companion object {
        private const val RC_SIGN_IN = 120
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_login_sign_up)

        btnLogin= findViewById(R.id.btnLogin)
        btnSignUp= findViewById(R.id.btnSignUp)
        loginWithGoogle= findViewById(R.id.loginWithGoogle)
        //  loginWithPhone= findViewById(R.id.loginWithPhone)

        // Configure Google Sign In
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Firebase Auth instance
        mAuth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            val intent = Intent(this@EntryLoginSignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        loginWithGoogle.setOnClickListener{
            signIn()
        }

        // loginWithPhone.setOnClickListener{
        //   val intent = Intent(this@EntryLoginSignupActivity, StartSignUpActivity::class.java)
        //   intent.putExtra("flag","phone");
        //   startActivity(intent)
        // }

        btnSignUp.setOnClickListener {
            val intent = Intent(this@EntryLoginSignUpActivity, SignUpActivity::class.java)
            //val intent = Intent(this@EntryLoginSignupActivity, StartSignUpActivity::class.java)
            //intent.putExtra("flag","email");
            startActivity(intent)
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
//            if (task.isSuccessful) {
            try {
                // Google Sign In was successful, authenticate with Firebase
                Toast.makeText(this,"Google Sign In Successful", Toast.LENGTH_SHORT).show();
                val account = task.getResult(ApiException::class.java)!!

                Log.e("UserName",">>"+account.idToken!!)
                firebaseAuthWithGoogle(account.idToken!!,account)

            } catch (e: ApiException) {
                Log.e("UserName1",">> "+e)
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this,"Google Sign In Failed",Toast.LENGTH_SHORT).show();
            }

//            }


        }
    }

    private fun firebaseAuthWithGoogle(idToken: String,account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser


                    val intent = Intent(this@EntryLoginSignUpActivity, PostGoogleSignUp::class.java)
                    intent.putExtra("account_email",account.email)
                    intent.putExtra("account_id",account.id)
                    //val intent = Intent(this, StartSignUpActivity::class.java)
                    //intent.putExtra("flag","google");
                    Toast.makeText(this,"Activity Started",Toast.LENGTH_SHORT).show();
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Sorry authentication failed!",Toast.LENGTH_SHORT).show();
                }
            }
    }
}