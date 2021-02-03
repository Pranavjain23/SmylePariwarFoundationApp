package com.application.smyleapp.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.application.smyleapp.R
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





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager2 = view.findViewById(R.id.needHelpViewPager)
        needHelpList.add(NeedHelp("https://www.imagesource.com/wp-content/uploads/2019/06/Rio.jpg","Book pilgrimage trips"))
        needHelpList.add(NeedHelp("https://www.imagesource.com/wp-content/uploads/2019/06/Rio.jpg","Educate 100 girls"))
        needHelpList.add(NeedHelp("https://www.imagesource.com/wp-content/uploads/2019/06/Rio.jpg","Support old and poor"))

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











//        recyclerNeedHelp = view.findViewById(R.id.recyclerNeedHelp)
//        needHelpDisplay()








    }
//    private fun needHelpDisplay(){
//
//        needHelpLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//
//        for (j in 0 until 5) {
//
//            val needHelpObject = NeedHelp(
//                "https://www.imagesource.com/wp-content/uploads/2019/06/Rio.jpg"
//            )
//            needHelpList.add(needHelpObject)
//
//            needHelpAdapter = NeedHelpAdapter(activity as Context, needHelpList)
//            recyclerNeedHelp.adapter = needHelpAdapter
//            recyclerNeedHelp.layoutManager = needHelpLayoutManager
//
//
//        }
//
//    }
