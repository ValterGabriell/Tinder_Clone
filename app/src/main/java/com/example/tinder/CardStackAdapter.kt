package com.example.tinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tinder.CardStackAdapter.ViewHolder
import com.example.tinder.Photo.PhotosItem
import com.example.tinder.Users.UsersItem
import com.squareup.picasso.Picasso

class CardStackAdapter(private var users: List<UsersItem>, var photos: List<PhotosItem>) :
    RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(users: UsersItem, usersPhotos: PhotosItem) {
            itemView.findViewById<TextView>(R.id.txtName).text = users.name
            itemView.findViewById<TextView>(R.id.txtCity).text = users.address.city
            Picasso.get().load(usersPhotos.url).into(itemView.findViewById<ImageView>(R.id.imageView2))
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view_tinder, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], photos[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}