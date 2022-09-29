package com.example.imdbapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbapp.R
import com.example.imdbapp.base.BaseViewHolder
import com.example.imdbapp.data.model.Results

class MainAdapter(
    private val context: Context,
    private val moviesList: List<Results>,
    private val clickListener: OnClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnClickListener {
        fun onClick(movie: Results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movies_row, parent, false)
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

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Results>(itemView) {
        override fun bind(item: Results, position: Int) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + item.posterPath)
                .into(itemView.findViewById(R.id.img_movie))
            itemView.findViewById<TextView>(R.id.txt_name).text = item.title
            itemView.findViewById<ImageView>(R.id.star_btn).setOnClickListener {
                it.setBackgroundResource(androidx.appcompat.R.drawable.abc_star_black_48dp)
                clickListener.onClick(item)
            }
        }
    }
}