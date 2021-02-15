package com.application.smyleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.smyleapp.R
import com.application.smyleapp.model.GoalGallery
import com.squareup.picasso.Picasso
import java.util.ArrayList

class GoalsAdapter (val context : Context,
                    val goalsList : ArrayList<GoalGallery>
) : RecyclerView.Adapter<GoalsAdapter.goalsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):goalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goals_gallery_single_row,parent,false)
        return goalsViewHolder(view)

    }

    override fun onBindViewHolder(holder: goalsViewHolder, position: Int) {

        val Goals = goalsList[position]
        Picasso.get().load(Goals.imgGoalGallery).error(R.drawable.smylelogo).into(holder.imgGoalGallery)

    }

    override fun getItemCount(): Int {
        return goalsList.size
    }
    class goalsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        //    val imgAppLogo : ImageView = view.findViewById(R.id.imgAppLogo)
        val imgGoalGallery : ImageView = view.findViewById(R.id.imgGoal)


    }




}