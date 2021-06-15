package com.hamada.sinwar.myproject2021.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.hamada.sinwar.myproject2021.models.MyMarker
import com.hamada.sinwar.myproject2021.util.Utils

class InfoListAdapter(val act: Activity, val app: NewsApplication):PagerAdapter() {

    val data:MutableList<MyMarker> = app.cityInfo

    override fun getCount(): Int {
        return data.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(act).inflate(R.layout.info_list_item, container, false)
        Utils().setupItem(act, view, data[position])
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

}