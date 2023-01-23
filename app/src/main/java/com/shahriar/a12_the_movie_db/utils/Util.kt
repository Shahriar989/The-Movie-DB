package com.shahriar.a12_the_movie_db.utils

object Util {

    fun posterUrlMake(uri: String?): String {
        return "https://image.tmdb.org/t/p/w780$uri"
    }
}
