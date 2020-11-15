package com.example.androidacademyhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.adapter.AdapterRecyclerViewMoveDetail

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_next_activity).setOnClickListener {
            startActivity(Intent(this,MovieDetailsActivity::class.java))
        }
    }
}