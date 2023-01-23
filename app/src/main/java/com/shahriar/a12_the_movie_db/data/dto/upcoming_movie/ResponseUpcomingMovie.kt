package com.shahriar.a12_the_movie_db.data.dto.upcoming_movie

import com.google.gson.annotations.SerializedName

data class ResponseUpcomingMovie(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieResults: List<MovieResult>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)
