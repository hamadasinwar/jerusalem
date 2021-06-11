package com.hamada.sinwar.myproject2021.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.ui.NewsActivity
import kotlinx.android.synthetic.main.fragment_onboarding1.view.*

class OnBoardingFragment1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_onboarding1, container, false)

        root.skip.setOnClickListener {
            openMain()
        }

        return root
    }
    private fun openMain() {
        startActivity(Intent(activity, NewsActivity::class.java))
        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}