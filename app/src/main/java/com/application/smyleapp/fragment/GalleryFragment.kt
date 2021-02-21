package com.application.smyleapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.smyleapp.R
import com.application.smyleapp.activity.AdminActivity

import com.application.smyleapp.adapter.GalleryImageAdapter
import com.application.smyleapp.adapter.GalleryImageClickListener
import com.application.smyleapp.adapter.Image
import kotlinx.android.synthetic.main.activity_gallery.*


class GalleryFragment : Fragment() , GalleryImageClickListener  {



    var x = 0

    lateinit var recyclerView : RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    //    lateinit var searchItem : androidx.appcompat.widget.SearchView
    lateinit var galleryAdapter: GalleryImageAdapter
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar : ProgressBar
    private val imageList = ArrayList<Image>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
//        val intent = Intent(activity as Context, GalleryActivity::class.java)
//        startActivity(intent)
        recyclerView = view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(activity)
//        searchItem = view.findViewById(R.id.searchItem)

//        progressBar = view.findViewById(R.id.progressBar)
//        progressLayout = view.findViewById(R.id.progressLayout)
//
//        progressLayout.visibility = View.VISIBLE

        if(x == 0){
            loadImages()
        }
        x = 1;

        galleryAdapter = GalleryImageAdapter(imageList)
        galleryAdapter.listener = this


        // init recyclerview

        recyclerView.layoutManager = GridLayoutManager(context , 3)
        recyclerView.adapter = galleryAdapter




        return view

    }

    private fun loadImages() {

        imageList.add(Image(R.drawable.as1))
        imageList.add(Image(R.drawable.as2))
        imageList.add(Image(R.drawable.as3))
        imageList.add(Image(R.drawable.as4))
        imageList.add(Image(R.drawable.as5))
        imageList.add(Image(R.drawable.as6))
        imageList.add(Image(R.drawable.mc1))
        imageList.add(Image(R.drawable.mc2))
        imageList.add(Image(R.drawable.mc3))
        imageList.add(Image(R.drawable.mc4))
        imageList.add(Image(R.drawable.mc5))
        imageList.add(Image(R.drawable.mc6))
        imageList.add(Image(R.drawable.mc7))
        imageList.add(Image(R.drawable.pc1))
        imageList.add(Image(R.drawable.pc2))
        imageList.add(Image(R.drawable.pc3))
        imageList.add(Image(R.drawable.pc4))
        imageList.add(Image(R.drawable.pc5))



    }
    override fun onClick(position: Int) {
        // handle click of image

        val bundle = Bundle()
        bundle.putSerializable("images", imageList)
        bundle.putInt("position", position)
        val galleryFragment = GalleryFullscreenFragment()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()

        galleryFragment.setArguments(bundle)
        if (fragmentTransaction != null) {
            galleryFragment.show(fragmentTransaction, "gallery")
        }
    }






}
