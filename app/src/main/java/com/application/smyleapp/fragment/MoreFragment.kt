package com.application.smyleapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.application.smyleapp.R
import com.application.smyleapp.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MoreFragment : Fragment() {

    lateinit var txtSignOut: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_more, container, false)
        txtSignOut = view.findViewById(R.id.txtSignOut)
        txtSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            

            activity?.finish()
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)

        }
        return view
    }
}