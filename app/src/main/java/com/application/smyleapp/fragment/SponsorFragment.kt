package com.application.smyleapp.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.smyleapp.R
import com.application.smyleapp.activity.PaymentActivity

class SponsorFragment : Fragment() {

    lateinit var btnDonateSponsor : Button
    lateinit var btnDonateSponsor1 : Button
    lateinit var btnContactNumber : Button
    lateinit var btnContactNumber1 : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sponsor, container, false)

        btnDonateSponsor = view.findViewById(R.id.btnDonateSponsor)

        btnContactNumber = view.findViewById(R.id.btnContactNumber)


        btnDonateSponsor.setOnClickListener {
            val intent = Intent(context, PaymentActivity::class.java)
            startActivity(intent)
        }

//        btnDonateSponsor1 .setOnClickListener {
//            val intent = Intent(context, PaymentActivity::class.java)
//            startActivity(intent)
//        }

        btnContactNumber.setOnClickListener {
            val u : Uri = Uri.parse("tel: "+9910191122)
            val intent = Intent(Intent.ACTION_DIAL,u)
            try
            {
                startActivity(intent);
            }
            catch ( s:SecurityException)
            {
                Toast.makeText(context, "Can not copy to dialer", Toast.LENGTH_LONG).show()
            }
        }

//        btnContactNumber1.setOnClickListener {
//            val u : Uri = Uri.parse("tel: "+9910191122)
//            val intent = Intent(Intent.ACTION_DIAL,u)
//            try
//            {
//                startActivity(intent);
//            }
//            catch ( s:SecurityException)
//            {
//                Toast.makeText(context, "Can not copy to dialer", Toast.LENGTH_LONG).show()
//            }
//        }


        return view

    }

}