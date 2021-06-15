package com.hamada.sinwar.myproject2021.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.InfoListAdapter
import com.hamada.sinwar.myproject2021.app.NewsApplication
import kotlinx.android.synthetic.main.fragment_info_list.view.*

class InfoListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_info_list, container, false)

        root.hicvp.adapter = InfoListAdapter(requireActivity(), requireActivity().application as NewsApplication)

        return root
    }

}