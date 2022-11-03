package com.example.datechnologies.model


import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datechnologies.R
import com.example.datechnologies.data.ChatLogMessageModel
import com.squareup.picasso.Picasso


class ChatAdapter( private val mList: List<ChatLogMessageModel>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ChatLog = mList[position]

        // sets the image to the imageview from our itemHolder class
        Picasso.get().load(Uri.parse(ChatLog.avatarUrl)).into(holder.imageView)
        // sets the text to the textview from our itemHolder class
        holder.nameView.text = ChatLog.name
        holder.messageView.text = ChatLog.message

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.avatar_image_view)
        val nameView: TextView = itemView.findViewById(R.id.chat_name)
        val messageView: TextView = itemView.findViewById(R.id.message_text_view)
    }


}