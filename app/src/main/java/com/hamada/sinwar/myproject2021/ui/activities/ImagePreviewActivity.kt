package com.hamada.sinwar.myproject2021.ui.activities

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.hamada.sinwar.myproject2021.R
import kotlinx.android.synthetic.main.activity_image_preview.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ImagePreviewActivity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)

        val url = intent.getStringExtra("preview")!!

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

        if (url.contains("firebase")){
            image.visibility = View.VISIBLE
            Glide.with(this@ImagePreviewActivity).load(url).into(image)
        }else if (url.contains("youtube")){
            ytPlayer.visibility = View.VISIBLE
            initializePlayer(getVideoID(url))
        }

    }

    private fun initializePlayer(videoID: String?){
        ytPlayer.initialize(getString(R.string.youtube_api_key), object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer,
                p2: Boolean
            ) {
                p1.loadVideo(videoID)
                p1.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@ImagePreviewActivity, "An error has occurred", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun getVideoID(url:String):String?{
        val pattern =
            "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"

        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher =
            compiledPattern.matcher(url) //url is youtube url for which you want to extract the id.

        if (matcher.find()) {
            return matcher.group()
        }else{
            return null
        }
    }

}