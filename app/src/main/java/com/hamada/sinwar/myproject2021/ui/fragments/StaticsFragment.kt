package com.hamada.sinwar.myproject2021.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.StaticsAdapter
import com.hamada.sinwar.myproject2021.app.NewsApplication
import kotlinx.android.synthetic.main.fragment_statics.view.*

class StaticsFragment : Fragment() {

    lateinit var app:NewsApplication

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_statics, container, false)

        app = requireActivity().application as NewsApplication
        val adapter = StaticsAdapter(app)
        root.rvStatics.layoutManager = GridLayoutManager(requireContext(), 2)
        root.rvStatics.adapter = adapter

        return root
    }

}