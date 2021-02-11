package com.application.smyleapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.smyleapp.R
import com.application.smyleapp.activity.AdminActivity
import com.application.smyleapp.activity.LoginActivity
import com.application.smyleapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class MoreFragment : Fragment() {

    lateinit var txtSignOut: TextView
    lateinit var btnAdminAccess : Button
    lateinit var txtShare : TextView
    lateinit var txtName : TextView
    lateinit var auth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_more, container, false)
        txtSignOut = view.findViewById(R.id.txtSignOut)
        txtShare = view.findViewById(R.id.txtShare)
        txtName = view.findViewById(R.id.txtName)
        btnAdminAccess = view.findViewById(R.id.btnAdminAccess)

        auth = FirebaseAuth.getInstance()

        val firebaseUser = auth.currentUser

        if (firebaseUser!=null){
            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    val currentUser = snapshot.getValue(User::class.java)
                    if (currentUser != null) {
                        txtName.setText(currentUser.userName)

                    }


                }
            })
        }



        txtSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()


            activity?.finish()
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)

        }

        btnAdminAccess.setOnClickListener {
            val intent = Intent(activity as Context, AdminActivity::class.java)
            startActivity(intent)
        }

        txtShare.setOnClickListener {
            shareApp()
        }
        return view
    }

    private fun shareApp(){
        val myIntent = Intent(Intent.ACTION_SEND)
        myIntent.type = "type/plain"
        val shareBody = ""
        val shareSub = ""
        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody)
        myIntent.putExtra(Intent.EXTRA_TEXT,shareSub)
        startActivity(Intent.createChooser(myIntent,"Share app"))

    }


}