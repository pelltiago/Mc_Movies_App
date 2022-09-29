package com.example.imdbapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbapp.R
import com.example.imdbapp.base.BaseViewHolder
import com.example.imdbapp.data.room.MovieRoom

class FavsAdapter(

    private val context: Context,
    private val moviesList: List<MovieRoom>,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.favs_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(moviesList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<MovieRoom>(itemView) {
        override fun bind(item: MovieRoom, position: Int) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + item.posterPath)
                .into(itemView.findViewById(R.id.img_movie))
            itemView.findViewById<TextView>(R.id.txt_name).text = item.title

        }
    }
}
