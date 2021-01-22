package com.application.smyleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.smyleapp.R
import com.application.smyleapp.model.NeedHelp
import com.squareup.picasso.Picasso
import java.util.ArrayList

class NeedHelpAdapter(val context : Context,
                             val needHelpList : ArrayList<NeedHelp>
) : RecyclerView.Adapter<NeedHelpAdapter.NeedHelpViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):NeedHelpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_need_help_row,parent,false)
        return NeedHelpViewHolder(view)

    }

    override fun onBindViewHolder(holder: NeedHelpViewHolder, position: Int) {

        val needHelp = needHelpList[position]
        Picasso.get().load(needHelp.imgNeedHelp).error(R.drawable.smylelogo).into(holder.imgNeedHelp)

    }

    override fun getItemCount(): Int {
        return needHelpList.size
    }
    class NeedHelpViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val imgNeedHelp : ImageView = view.findViewById(R.id.imgNeedHelp)
        val btnDonate : Button = view.findViewById(R.id.btnDonate)


    }




}