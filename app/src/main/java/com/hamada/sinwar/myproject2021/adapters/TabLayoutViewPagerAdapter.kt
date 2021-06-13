package com.hamada.sinwar.myproject2021.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hamada.sinwar.myproject2021.ui.fragments.InfoListFragment
import com.hamada.sinwar.myproject2021.ui.fragments.MapFragment

class TabLayoutViewPagerAdapter(private val myContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

        var fragment:Fragment? = null

        when(position){
            0->{fragment = MapFragment()}
            1->{fragment = InfoListFragment()}
        }
        return fragment!!
    }
}