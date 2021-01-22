package com.application.smyleapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.smyleapp.R
import com.application.smyleapp.adapter.NeedHelpAdapter
import com.application.smyleapp.model.NeedHelp

class HomeFragment : Fragment() {

    lateinit var recyclerNeedHelp: RecyclerView
    lateinit var needHelpAdapter: NeedHelpAdapter
    lateinit var needHelpLayoutManager: LinearLayoutManager

    val needHelpList = arrayListOf<NeedHelp>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        recyclerNeedHelp = view.findViewById(R.id.recyclerNeedHelp)
        needHelpDisplay()







        return view
    }
    private fun needHelpDisplay(){

        needHelpLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        for (j in 0 until 5) {

            val needHelpObject = NeedHelp(
                "https://www.imagesource.com/wp-content/uploads/2019/06/Rio.jpg"
            )
            needHelpList.add(needHelpObject)

            needHelpAdapter = NeedHelpAdapter(activity as Context, needHelpList)
            recyclerNeedHelp.adapter = needHelpAdapter
            recyclerNeedHelp.layoutManager = needHelpLayoutManager


        }

    }
}