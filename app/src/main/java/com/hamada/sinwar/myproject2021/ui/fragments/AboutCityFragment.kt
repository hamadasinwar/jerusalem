package com.hamada.sinwar.myproject2021.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.TabLayoutViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_about_city.view.*

class AboutCityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_about_city, container, false)

        val sharedPref = requireActivity().getSharedPreferences("com.hamada.sinwar", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        if (sharedPref.getString("infoType", null) == "map"){
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.info, MapFragment()).commit()
        }else{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.info, InfoListFragment()).commit()
        }

        root.infoSwitch.setOnClickListener {
            val type = sharedPref.getString("infoType", null)
            if (type == "map"){
                root.infoSwitch.setImageResource(R.drawable.ic_map)
                editor.putString("infoType", "list")
                editor.apply()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.info, InfoListFragment()).commit()
            }else if (type == "list"){
                root.infoSwitch.setImageResource(R.drawable.ic_list)
                editor.putString("infoType", "map")
                editor.apply()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.info, MapFragment()).commit()
            }
        }

        return root
    }
}