package com.hamada.sinwar.myproject2021.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.TabLayoutViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_about_city.view.*

class AboutCityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_about_city, container, false)

        root.tabLayout!!.addTab(root.tabLayout!!.newTab().setText("Map"))
        root.tabLayout!!.addTab(root.tabLayout!!.newTab().setText("List"))
        root.tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabLayoutViewPagerAdapter(requireContext(), requireActivity().supportFragmentManager)
        root.viewPager!!.adapter = adapter

        root.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(root.tabLayout))
        root.tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                root.viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        return root
    }
}