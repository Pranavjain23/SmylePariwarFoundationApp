package com.application.smyleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.application.smyleapp.R
import com.application.smyleapp.model.GoalGallery
import com.application.smyleapp.model.NeedHelp
import com.squareup.picasso.Picasso

class SliderAdapter_GoalsGallery internal constructor(
    goalsList : MutableList<GoalGallery>,
    viewPager2: ViewPager2

) : RecyclerView.Adapter<GoalsAdapter.goalsViewHolder>(){

    private val goalsList : List<GoalGallery>
    private val viewPager2 : ViewPager2

    init {
        this.goalsList = goalsList
        this.viewPager2 = viewPager2
    }


    class sliderViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val cardNeedHelp : CardView = view.findViewById(R.id.cardNeedHelp)
        val imgGoalGallery : ImageView = view.findViewById(R.id.imgGoal)

        fun image(Goals: GoalGallery){
            Picasso.get().load(Goals.imgGoalGallery).error(R.drawable.smylelogo).into(imgGoalGallery)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GoalsAdapter.goalsViewHolder {
        return GoalsAdapter.goalsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.goals_gallery_single_row,parent,false)

        )

    }

    override fun getItemCount(): Int {
        return goalsList.size
    }
    private val runnable = Runnable {
        goalsList.addAll(goalsList)
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: GoalsAdapter.goalsViewHolder, position: Int) {
        val Goals = goalsList[position]
        Picasso.get().load(Goals.imgGoalGallery).error(R.drawable.smylelogo).into(holder.imgGoalGallery)
        if(position == goalsList.size - 2){
            viewPager2.post(runnable)

        }


    }
}