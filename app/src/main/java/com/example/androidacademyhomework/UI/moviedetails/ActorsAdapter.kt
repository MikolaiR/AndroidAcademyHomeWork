package com.example.androidacademyhomework.UI.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidacademyhomework.BuildConfig
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.context
import com.example.androidacademyhomework.data.model.MovieActors
import com.example.androidacademyhomework.fullUrl

class ActorsAdapter : RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    private var actors = listOf<MovieActors>()

    fun bindActors(newActors: List<MovieActors>) {
        actors = newActors
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    inner class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val imageViewActor = itemView.findViewById<ImageView>(R.id.imageViewActor)
        private val textViewNameActor = itemView.findViewById<TextView>(R.id.textViewNameActor)

        fun onBind(actor: MovieActors) {
            Glide.with(context)
                .load(actor.profilePath?.fullUrl())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.error_image)
                .into(imageViewActor)

            textViewNameActor.text = actor.name
        }
    }
}

