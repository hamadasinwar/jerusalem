package com.hamada.sinwar.myproject2021.ui.activities

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.Transition
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hamada.sinwar.myproject2021.R
import kotlinx.android.synthetic.main.activity_image_preview.*

class ImagePreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)

        val url = intent.getStringExtra("preview")!!
        Glide.with(this@ImagePreviewActivity).load(url).into(image)

        image.setOnClickListener {
            onBackPressed()
        }

        window.sharedElementEnterTransition.addListener(object : Transition.TransitionListener {

            private var isClosing = false


            override fun onTransitionStart(transition: Transition?) {
                if (isClosing) {
                    card.radius = 25f
                }
            }

            override fun onTransitionEnd(transition: Transition?) {
                if (!isClosing) {
                    isClosing = true
                    ObjectAnimator.ofFloat(card, "radius", 0f).setDuration(50).start()
                }
            }

            override fun onTransitionCancel(transition: Transition?) {}
            override fun onTransitionPause(transition: Transition?) {}
            override fun onTransitionResume(transition: Transition?) {}

        })

    }
}