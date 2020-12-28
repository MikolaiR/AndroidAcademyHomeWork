package com.example.androidacademyhomework.repository

import android.app.Application
import com.example.androidacademyhomework.data.loadMovies

class MovieRepository(private val application: Application) {

  suspend fun loadMovies() = loadMovies(application)

}