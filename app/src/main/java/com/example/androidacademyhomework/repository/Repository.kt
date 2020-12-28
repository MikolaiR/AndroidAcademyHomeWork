package com.example.androidacademyhomework.repository

import android.app.Application
import com.example.androidacademyhomework.data.loadMovies

class Repository(private val application: Application) {

  suspend fun loadMovies() = loadMovies(application)

}