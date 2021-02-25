package com.application.smyleapp.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.application.smyleapp.R

class AboutFragment : Fragment() {

    lateinit var facebook : TextView
    lateinit var youtube : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        facebook = view.findViewById(R.id.read)
        youtube = view.findViewById(R.id.watch)

        facebook.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            val uri =
                Uri.parse("https://www.facebook.com/groups/1059347210847449")
            intent.data = uri
            startActivity(intent)
        })

        youtube.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            val uri =
                Uri.parse("") //https://www.youtube.com/channel/$key
            intent.data = uri
            startActivity(intent)
        })


        return view

    }

}