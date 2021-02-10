package com.application.smyleapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.application.smyleapp.R
import com.application.smyleapp.adapter.SliderAdapter_welcome
import com.google.firebase.auth.FirebaseAuth

class SliderActivity : AppCompatActivity() {
    lateinit var mSlideViewPager: ViewPager
    lateinit var mDotLayout: LinearLayout

    lateinit var mNextBtn: Button
    lateinit var mBackBtn: Button
    lateinit var btnJoinUs : Button

    private var mCurrentPage = 0

    private lateinit var mDots: Array<TextView?>
    lateinit var mAuth : FirebaseAuth
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)



        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.dotsLayout)

        mNextBtn = findViewById(R.id.nextBtn)
        mBackBtn = findViewById(R.id.prevBtn)
        btnJoinUs = findViewById(R.id.btnJoinUs)

        btnJoinUs.setOnClickListener {
            val intent = Intent(this@SliderActivity, EntryLoginSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        val sliderAdapter = SliderAdapter_welcome(this)

        mSlideViewPager.adapter = sliderAdapter

        addDotsIndicator(0)

        mSlideViewPager.addOnPageChangeListener(viewListener)
        mNextBtn.setOnClickListener {
            mSlideViewPager.currentItem = mCurrentPage + 1
        }


        mBackBtn.setOnClickListener {
            mSlideViewPager.currentItem = mCurrentPage - 1
        }

    }

    fun addDotsIndicator(position: Int) {
        mDots = arrayOfNulls(3)
        mDotLayout.removeAllViews()
        for (i in mDots.indices) {
            mDots[i] = TextView(this)
            mDots[i]!!.text = Html.fromHtml("&#8226;")
            mDots[i]!!.textSize = 35f
            mDots[i]!!.setTextColor(resources.getColor(R.color.colorPrimary))
            mDotLayout.addView(mDots[i])
        }
        if (mDots.isNotEmpty()) {
            mDots[position]!!.setTextColor(resources.getColor(R.color.black))
        }
    }

    var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDotsIndicator(position)
            mCurrentPage = position
            if (position == 0) {
                mNextBtn.isEnabled = true
                mBackBtn.isEnabled = false
                mBackBtn.visibility = View.INVISIBLE
                mNextBtn.text = "Next"
                mBackBtn.text = ""
            } else if (position == mDots.size - 1) {
                mNextBtn.isEnabled = true
                mBackBtn.isEnabled = true
                //mBackBtn.visibility = View.VISIBLE
                mNextBtn.text = "Finish"
                mBackBtn.text = "Back"

                mNextBtn.setOnClickListener {
                    val intent = Intent(this@SliderActivity, EntryLoginSignUpActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            } else {
                mNextBtn.isEnabled = true
                mBackBtn.isEnabled = true
                //mBackBtn.visibility = View.VISIBLE
                mNextBtn.text = "Next"
                mBackBtn.text = "Back"
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}

    }
    public override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth!!.currentUser
        if (currentUser != null){
                        val intent = Intent(
                this@SliderActivity,
                MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }

    }

}