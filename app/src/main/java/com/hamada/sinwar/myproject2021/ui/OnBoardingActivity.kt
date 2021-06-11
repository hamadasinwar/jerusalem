package com.hamada.sinwar.myproject2021.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.ScreenPagerAdapter
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val viewPagerAdapter = ScreenPagerAdapter(supportFragmentManager)
        liquidPager.adapter = viewPagerAdapter
        liquidPager.startAnimation(anim)

    }
}