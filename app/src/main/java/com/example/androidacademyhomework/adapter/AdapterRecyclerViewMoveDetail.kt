package com.example.androidacademyhomework.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.R

class AdapterRecyclerViewMoveDetail(private val context: Context) :
    RecyclerView.Adapter<AdapterRecyclerViewMoveDetail.MovieViewHolder>() {

   private val listForRecycler = listOf<Actors>(
        Actors(R.drawable.movie, R.string.name_after),
        Actors(R.drawable.movie_two, R.string.chris_evans),
        Actors(R.drawable.movie_three, R.string.mark_ruffal),
        Actors(R.drawable.movie_four, R.string.chris_hemsw)
    )

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewAfterItem: ImageView = itemView.findViewById(R.id.image_view_after_item)
        val textViewAfterItem: TextView = itemView.findViewById(R.id.text_view_after_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.recycler_item_movie_details, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listForRecycler.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val actor = listForRecycler[position]
        holder.imageViewAfterItem.setImageResource(actor.imageResource)
        holder.textViewAfterItem.text = context.resources.getString(actor.namActor)

    }
}

data class Actors(
    val imageResource: Int,
    val namActor: Int
)

