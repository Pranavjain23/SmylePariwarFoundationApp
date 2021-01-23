package com.application.smyleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.application.smyleapp.R
import com.application.smyleapp.model.NeedHelp
import com.squareup.picasso.Picasso
import java.util.ArrayList

class SliderAdapter_needHelp internal constructor(
    needHelpList : MutableList<NeedHelp>,
    viewPager2: ViewPager2

) : RecyclerView.Adapter<NeedHelpAdapter.NeedHelpViewHolder>(){

    private val needHelpList : List<NeedHelp>
    private val viewPager2 : ViewPager2

    init {
        this.needHelpList = needHelpList
        this.viewPager2 = viewPager2
    }


    class sliderViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val cardNeedHelp : CardView = view.findViewById(R.id.cardNeedHelp)
        private val imgNeedHelp : ImageView = view.findViewById(R.id.imgNeedHelp)

        fun image(needHelp: NeedHelp){
            Picasso.get().load(needHelp.imgNeedHelp).error(R.drawable.smylelogo).into(imgNeedHelp)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NeedHelpAdapter.NeedHelpViewHolder {
        return NeedHelpAdapter.NeedHelpViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_need_help_row,parent,false)

        )

    }

    override fun getItemCount(): Int {
        return needHelpList.size
    }
    private val runnable = Runnable {
        needHelpList.addAll(needHelpList)
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: NeedHelpAdapter.NeedHelpViewHolder, position: Int) {
        val needHelp = needHelpList[position]
        Picasso.get().load(needHelp.imgNeedHelp).error(R.drawable.smylelogo).into(holder.imgNeedHelp)
        if(position == needHelpList.size - 2){
            viewPager2.post(runnable)

        }


    }
}





