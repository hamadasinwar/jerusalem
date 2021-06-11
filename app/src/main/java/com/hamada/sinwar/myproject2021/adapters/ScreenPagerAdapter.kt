package com.hamada.sinwar.myproject2021.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hamada.sinwar.myproject2021.ui.fragments.OnBoardingFragment1
import com.hamada.sinwar.myproject2021.ui.fragments.OnBoardingFragment2

class ScreenPagerAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment:Fragment? = null
        when (position){
            0->{ fragment = OnBoardingFragment1() }
            1->{ fragment = OnBoardingFragment2() }
        }
        return fragment!!
    }
}