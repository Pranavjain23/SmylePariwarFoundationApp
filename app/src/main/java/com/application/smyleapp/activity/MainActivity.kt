package com.application.smyleapp.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.smyleapp.R
import com.application.smyleapp.fragment.*
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*

val homeFragment = HomeFragment()
class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val AboutFragment = AboutFragment()
        val GalleryFragment = GalleryFragment()
        val SponsorFragment = SponsorFragment()
        val MoreFragment = MoreFragment()



        bottom_navigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_home))
        bottom_navigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_about))
        bottom_navigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_sponsor))
        bottom_navigation.add(MeowBottomNavigation.Model(4,R.drawable.ic_gallery))
        bottom_navigation.add(MeowBottomNavigation.Model(5,R.drawable.ic_more))
        makeCurrentFragment(homeFragment)

        bottom_navigation.show(1)

        bottom_navigation.setOnClickMenuListener{
            when(it.id){
                1 -> makeCurrentFragment(homeFragment)
                2 -> makeCurrentFragment(AboutFragment)
                4 -> makeCurrentFragment(GalleryFragment)
                3 -> makeCurrentFragment(SponsorFragment)
                5 -> makeCurrentFragment(MoreFragment)
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
    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag) {
            !is HomeFragment ->    {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout, homeFragment)
                    commit()
                }
                bottom_navigation.show(1)

            }

            else -> super.onBackPressed()
        }
    }

//    override fun onClick(position: Int) {
//        // handle click of image
//
//        Toast.makeText(this@MainActivity,"hello", Toast.LENGTH_LONG).show()
//        val bundle = Bundle()
////        bundle.putSerializable("images", imageList)
//        bundle.putInt("position", position)
//        val galleryFragment = GalleryFullscreenFragment()
//        val fragmentTransaction = supportFragmentManager?.beginTransaction()
//
//        galleryFragment.setArguments(bundle)
//        if (fragmentTransaction != null) {
//            galleryFragment.show(fragmentTransaction, "gallery")
//        }
//    }


    }


