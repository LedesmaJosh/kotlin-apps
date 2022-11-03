package com.example.datechnologies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.os.HandlerCompat.postDelayed
import com.example.datechnologies.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        setTitle("Animation")
        setupActionBar()

        var listener = View.OnTouchListener(function = {view, motionEvent ->
                if(motionEvent.action == MotionEvent.ACTION_MOVE){
                    view.y = motionEvent.rawY - view.height/2
                    view.x = motionEvent.rawX - view.width/2
                }
            true
        })
        animation_image_view.setOnTouchListener(listener)
        setUpFadeButton()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)
        }
    }
    fun setUpFadeButton(){
        animation_fade_button.setOnClickListener {
            val fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in)
            val fadeOutAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_out)
            animation_image_view.startAnimation(fadeOutAnimation)
            Handler().postDelayed({
                animation_image_view.startAnimation(fadeInAnimation)
            },2000)
        }
    }
}