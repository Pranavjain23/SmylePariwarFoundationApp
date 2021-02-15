package com.application.smyleapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.smyleapp.R
import com.application.smyleapp.model.Contributor
import com.application.smyleapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_send_message.*


class AdminActivity : AppCompatActivity() {
    lateinit var adminName : EditText
    lateinit var adminNumber  :EditText
    lateinit var adminKey :EditText
    lateinit var adminEnter : Button
    lateinit var auth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    lateinit var phone_number : String

    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        adminName = findViewById(R.id.adminName)
        adminNumber = findViewById(R.id.adminNumber)
        adminKey = findViewById(R.id.adminKey)
        adminEnter = findViewById(R.id.adminEnter)
        auth = FirebaseAuth.getInstance()
        phone_number = "000"

        val firebaseUser = auth.currentUser

        if (firebaseUser!=null){
            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@AdminActivity, error.message, Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    val currentUser = snapshot.getValue(User::class.java)
                    if (currentUser != null) {
                        Log.e("hello",currentUser.phoneNumber+"is hello")
                        phone_number = currentUser.phoneNumber


                    }


                }
            })
        }





        databaseReference = FirebaseDatabase.getInstance().getReference("Admins")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminActivity, error.message, Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child(phone_number).exists()) {
                    val intent = Intent(
                        this@AdminActivity,
                        SendMessageActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }else{
                    progressLayout.visibility = View.GONE


                }



            }
        })



        adminEnter.setOnClickListener {
            addAdmin()
        }










    }
    private fun addAdmin(){
        if(adminKey.text.toString() == "smyle9990908555"){
            databaseReference = FirebaseDatabase.getInstance().getReference("Admins").child(adminNumber.text.toString())
            val hashMap:HashMap<String,String> = HashMap()

            hashMap.put("adminName",adminName.text.toString())
            hashMap.put("adminNumber",adminNumber.text.toString())
            databaseReference.setValue(hashMap).addOnCompleteListener(this){


                if (it.isSuccessful){


                    //open home activity
                    adminName.setText("")
                    adminNumber.setText("")
                    adminKey.setText("")


                    val intent = Intent(
                        this@AdminActivity,
                        SendMessageActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(applicationContext,"admin login failed ${it.result}", Toast.LENGTH_SHORT).show()
                    Log.d("ERROR","${it}")

                }
            }
        }else{
            Toast.makeText(applicationContext,"Incorrect key", Toast.LENGTH_SHORT).show()

        }

    }

}