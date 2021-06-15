package com.hamada.sinwar.myproject2021.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daasuu.cat.CountAnimationTextView
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.jackandphantom.circularprogressbar.CircleProgressbar
import kotlinx.android.synthetic.main.statics_item.view.*

class StaticsAdapter(app:NewsApplication):RecyclerView.Adapter<StaticsAdapter.StaticsViewHolder>() {

    private val staticsInfo = app.staticsInfo

    inner class StaticsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val text: TextView = itemView.tvStatics
        val progressBar: CircleProgressbar = itemView.circleProgressbar
        val textProgress: CountAnimationTextView = itemView.tvCountAnimation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaticsViewHolder {
        return StaticsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.statics_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return staticsInfo.size
    }

    override fun onBindViewHolder(holder: StaticsViewHolder, position: Int) {
        holder.text.text = staticsInfo[position].text
        holder.textProgress.setAnimationDuration(2500).countAnimation(0, staticsInfo[position].progress)
        holder.progressBar.setProgressWithAnimation(100F, 2500)
    }

}