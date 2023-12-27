package com.example.gitapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var contexts: Context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = "Repository Name : " + database.Title.get(position)
        holder.create.text = "Created At : " + database.Created_date.get(position)
        holder.close.text = "Closed At : " + database.closed_date.get(position)
    }

    override fun getItemCount(): Int {
        return database.Title.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val create: TextView = itemView.findViewById(R.id.create)
        val close: TextView = itemView.findViewById(R.id.close)
    }
}
