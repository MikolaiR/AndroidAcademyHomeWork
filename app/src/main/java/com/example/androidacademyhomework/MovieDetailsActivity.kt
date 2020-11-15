package com.example.androidacademyhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.adapter.AdapterRecyclerViewMoveDetail

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var  recyclerActors: RecyclerView
    private lateinit var adapterRecyclerViewMoveDetail: AdapterRecyclerViewMoveDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        /*recyclerActors = findViewById(R.id.recycler_actors)
        adapterRecyclerViewMoveDetail = AdapterRecyclerViewMoveDetail(this)
        recyclerActors.apply {
            layoutManager = LinearLayoutManager(this@MovieDetailsActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterRecyclerViewMoveDetail
        }*/
    }
}