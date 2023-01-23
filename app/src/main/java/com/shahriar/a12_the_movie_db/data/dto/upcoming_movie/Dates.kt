package com.shahriar.a12_the_movie_db.data.dto.upcoming_movie

import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)
