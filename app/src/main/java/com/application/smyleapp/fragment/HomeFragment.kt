package com.application.smyleapp.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.application.smyleapp.R
import com.application.smyleapp.activity.MainActivity
import com.application.smyleapp.adapter.NeedHelpAdapter
import com.application.smyleapp.adapter.SliderAdapter_needHelp
import com.application.smyleapp.model.NeedHelp
import java.lang.Math.abs


class HomeFragment : Fragment() {

    lateinit var recyclerNeedHelp: RecyclerView
    lateinit var needHelpAdapter: NeedHelpAdapter
    lateinit var needHelpLayoutManager: LinearLayoutManager
    lateinit var viewPager2: ViewPager2
    lateinit var sliderAdapter : SliderAdapter_needHelp
    private val sliderHandler = Handler()

    val needHelpList = arrayListOf<NeedHelp>()

    lateinit var card1 : CardView
    lateinit var card2 : CardView
    lateinit var card3 : CardView
    lateinit var card4 : CardView
    lateinit var card5 : CardView
    lateinit var card6 : CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        card1 = view.findViewById(R.id.card1)
        card2 = view.findViewById(R.id.card2)
        card3 = view.findViewById(R.id.card3)
        card4 = view.findViewById(R.id.card4)
        card5 = view.findViewById(R.id.card5)
        card6 = view.findViewById(R.id.card6)

        card1.setOnClickListener {

            val fragment = Goal1Fragment()
            val args = Bundle()
            fragment.arguments = args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    fragment
                ).commit()
        }
        card2.setOnClickListener {
            val fragment = Goal2Fragment()
            val args = Bundle()
            fragment.arguments = args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    fragment
                ).commit()
        }
        card3.setOnClickListener {
            val fragment = Goal3Fragment()
            val args = Bundle()
            fragment.arguments = args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    fragment
                ).commit()
        }
        card4.setOnClickListener {
            val fragment = Goal4Fragment()
            val args = Bundle()
            fragment.arguments = args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    fragment
                ).commit()
        }
        card5.setOnClickListener {
            val fragment = Goal5Fragment()
            val args = Bundle()
            fragment.arguments = args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    fragment
                ).commit()
        }
        card6.setOnClickListener {
            val fragment = Goal6Fragment()
            val args = Bundle()
            fragment.arguments = args
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frameLayout,
                    fragment
                ).commit()
        }


        viewPager2 = view.findViewById(R.id.needHelpViewPager)
        needHelpList.add(NeedHelp(R.drawable.smylelogo,"hello"))
        needHelpList.add(NeedHelp(R.drawable.smylelogo,"Educate 100 girls"))
        needHelpList.add(NeedHelp(R.drawable.smylelogo,"Support old and poor"))

        viewPager2.adapter = SliderAdapter_needHelp(needHelpList, viewPager2)

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.25f

        }
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
        return view
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable,3000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable,3000)
    }



}

