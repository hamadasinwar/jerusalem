package com.hamada.sinwar.myproject2021.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.hamada.sinwar.myproject2021.models.MyMarker
import com.hamada.sinwar.myproject2021.ui.activities.ImagePreviewActivity
import java.util.regex.Matcher
import java.util.regex.Pattern

class InfoListAdapter(private val act: Activity, val app: NewsApplication):PagerAdapter() {

    private val data:MutableList<MyMarker> = app.cityInfo

    init {
        data.shuffle()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(act).inflate(R.layout.info_list_item, container, false)
        setupItem(act, view, data[position])
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    private fun setupItem(activity: Activity, view: View, obj: MyMarker){
        val txt = view.findViewById<TextView>(R.id.txt_item)
        txt.text = obj.title

        val txt2 = view.findViewById<TextView>(R.id.txt)
        txt2.text = obj.snippet

        val img = view.findViewById<ImageView>(R.id.img_item)
        val arrow = view.findViewById<ImageView>(R.id.playArrow)
        if (obj.image!!.contains("youtube")){
            val url = "https://img.youtube.com/vi/${getVideoID(obj.image!!)}/0.jpg"
            Glide.with(view).load(url).into(img)
            arrow.visibility = View.VISIBLE
        }else{
            Glide.with(view).load(obj.image).into(img)
        }

        view.setOnClickListener {
            val intent = Intent(activity, ImagePreviewActivity::class.java)
            intent.putExtra("preview", "${obj.image}")
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, img, "shared")
            activity.startActivity(intent, options.toBundle())
        }
    }
    private fun getVideoID(url:String):String?{
        val pattern =
            "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"

        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher =
            compiledPattern.matcher(url)

        return if (matcher.find()) {
            matcher.group()
        }else{
            null
        }
    }

}