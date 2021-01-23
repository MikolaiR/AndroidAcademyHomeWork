package com.example.androidacademyhomework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.ui.movielist.MoviesViewHolder
import com.example.androidacademyhomework.data.model.Movie

class MovieAdapter(private val clickListener: OnRecyclerItemClicked) :
    PagingDataAdapter<Movie, MoviesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.onBind(movie)
            holder.itemView.setOnClickListener {
                clickListener.onClick(movie)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
              return  oldItem == newItem
            }
        }
    }
}

interface OnRecyclerItemClicked {
    fun onClick(movie: Movie)
}
