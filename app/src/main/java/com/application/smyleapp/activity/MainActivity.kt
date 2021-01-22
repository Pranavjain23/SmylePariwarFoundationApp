package com.application.smyleapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.application.smyleapp.R
import com.application.smyleapp.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val AboutFragment = AboutFragment()
        val GalleryFragment = GalleryFragment()
        val SponsorFragment = SponsorFragment()
        val MoreFragment = MoreFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.about -> makeCurrentFragment(AboutFragment)
                R.id.gallery -> makeCurrentFragment(GalleryFragment)
                R.id.sponsor -> makeCurrentFragment(SponsorFragment)
                R.id.more -> makeCurrentFragment(MoreFragment)
            }
            true
        }




    }
    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout,fragment)
            commit()
        }
    }
    }
