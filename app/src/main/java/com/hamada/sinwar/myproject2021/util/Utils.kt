package com.hamada.sinwar.myproject2021.util

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.models.MyMarker
import com.hamada.sinwar.myproject2021.ui.ImagePreviewActivity

class Utils {
    fun setupItem(activity: Activity, view: View, obj: MyMarker){
        val txt = view.findViewById<TextView>(R.id.txt_item)
        txt.text = obj.title

        val txt2 = view.findViewById<TextView>(R.id.txt)
        txt2.text = obj.snippet

        val img = view.findViewById<ImageView>(R.id.img_item)
        Glide.with(view).load(obj.image).into(img)

        view.setOnClickListener {
            val intent = Intent(activity, ImagePreviewActivity::class.java)
            intent.putExtra("preview", "${obj.image}")
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, img, "shared")
            activity.startActivity(intent, options.toBundle())
        }
    }
}