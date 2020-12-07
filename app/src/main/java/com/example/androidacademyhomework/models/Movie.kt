package com.example.androidacademyhomework.models

data class Movie (
    val id:Int,
    val banner:String,
    val favorites:Boolean,
    val ageLimit:Int,
    val genre:String,
    val rating:Int,
    val reviews:Int,
    val movieName:String,
    val story:String,
    val movieDuration:Int,
    val actors:List<Actor>
)