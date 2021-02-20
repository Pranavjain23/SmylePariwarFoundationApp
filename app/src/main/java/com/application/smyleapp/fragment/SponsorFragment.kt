package com.application.smyleapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.application.smyleapp.R
import com.application.smyleapp.activity.PaymentActivity

class SponsorFragment : Fragment() {

    lateinit var btnDonateSponsor : Button
    lateinit var btnDonateSponsor1 : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sponsor, container, false)

        btnDonateSponsor = view.findViewById(R.id.btnDonateSponsor)
        btnDonateSponsor1 = view.findViewById(R.id.btnDonateSponsor1)

        btnDonateSponsor.setOnClickListener {
            val intent = Intent(context, PaymentActivity::class.java)
            startActivity(intent)
        }

        btnDonateSponsor1 .setOnClickListener {
            val intent = Intent(context, PaymentActivity::class.java)
            startActivity(intent)
        }


        return view

    }

}