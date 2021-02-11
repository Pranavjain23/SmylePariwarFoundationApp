package com.application.smyleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.application.smyleapp.R

class SliderAdapter_welcome(val context: Context) : PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater
    lateinit var slideImageView : ImageView
    lateinit var slideHeading : TextView
    lateinit var slideDescription : TextView




    val slide_images = arrayOf(

        R.drawable.donate,
        R.drawable.helpchild,
        R.drawable.bevolunteer
    )

    val slide_headings = arrayOf(
        "GET INVOLVED",
        "HELP THE CHILD",
        "BECOME A VOLUNTEER"
    )

    val slide_descs = arrayOf(
        "Donate for a cause as only you can do it and help those in urgent need",
        "Help poor children fight their way out of poverty by donating for their education",
        "Help us fighting our goals and make the world better place to live in for each and everyone"
    )


    override fun getCount(): Int {
        return slide_headings.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater.inflate(R.layout.slide_layout, container, false)

        slideImageView =  view.findViewById(R.id.imgSlider)
        slideHeading = view.findViewById(R.id.sliderHeading)
        slideDescription= view.findViewById(R.id.sliderDesc)

        slideImageView.setImageResource(slide_images[position])
        slideHeading.text = slide_headings[position]
        slideDescription.text = slide_descs[position]

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }



}