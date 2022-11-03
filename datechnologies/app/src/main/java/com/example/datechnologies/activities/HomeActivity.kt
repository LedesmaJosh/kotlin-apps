package com.example.datechnologies.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datechnologies.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle(R.string.activity_main_title)

        setUpButtons()
    }

    fun setUpButtons(){
        home_login_button.setOnClickListener { navigateToLogin() }
        home_chat_button.setOnClickListener { navigateToChat() }
        home_animation_button.setOnClickListener { navigateToAnimation() }
    }
    fun navigateToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))

    }
    fun navigateToChat(){
        startActivity(Intent(this, ChatActivity::class.java))

    }
    fun navigateToAnimation(){
        startActivity(Intent(this, AnimationActivity::class.java))

    }
}