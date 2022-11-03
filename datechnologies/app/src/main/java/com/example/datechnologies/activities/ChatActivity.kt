package com.example.datechnologies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datechnologies.R
import com.example.datechnologies.model.ChatAdapter
import com.example.datechnologies.model.RapptrViewModel
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    private val viewModel = RapptrViewModel()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setupActionBar()
        setUpRecyclerView()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)
        }
    }

    fun setUpRecyclerView(){
        viewModel.retrieveMessages()
        recyclerView = recycler_View
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.allMessages.observe(this){messages->
            val adapter = ChatAdapter(messages)
            recyclerView.adapter = adapter
        }



    }


}