package com.application.smyleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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

) : RecyclerView.Adapter<SliderAdapter_needHelp.sliderViewHolder>(){

    private val needHelpList : List<NeedHelp>
    private val viewPager2 : ViewPager2

    init {
        this.needHelpList = needHelpList
        this.viewPager2 = viewPager2
    }


    class sliderViewHolder(view : View) : RecyclerView.ViewHolder(view){
         val cardNeedHelp : CardView = view.findViewById(R.id.cardNeedHelp)
         val imgNeedHelp : ImageView = view.findViewById(R.id.imgNeedHelp)
        val txtNeedHelp : TextView = view.findViewById(R.id.txtNeedHelp)

        fun image(needHelp: NeedHelp){
            Picasso.get().load(needHelp.imgNeedHelp).error(R.drawable.smylelogo).into(imgNeedHelp)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter_needHelp.sliderViewHolder {
        return SliderAdapter_needHelp.sliderViewHolder(
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



    override fun onBindViewHolder(holder: SliderAdapter_needHelp.sliderViewHolder, position: Int) {
        val needHelp = needHelpList[position]

        Picasso.get().load(needHelp.imgNeedHelp).error(R.drawable.smylelogo).into(holder.imgNeedHelp)
        holder.txtNeedHelp.text = needHelp.title

        if(position == needHelpList.size - 2){
            viewPager2.post(runnable)

        }


    }
}





