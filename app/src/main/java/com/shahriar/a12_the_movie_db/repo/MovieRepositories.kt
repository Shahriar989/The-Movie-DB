package com.shahriar.a12_the_movie_db.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.shahriar.a12_the_movie_db.api.ApiServices
import com.shahriar.a12_the_movie_db.data.dto.latest_movie.ResponseLatestMovieDTO
import com.shahriar.a12_the_movie_db.paging.UpcomingMoviePagingSource
import javax.inject.Inject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieRepositories @Inject constructor(private val apiServices: ApiServices) {

    fun getUpcomingMovie() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { UpcomingMoviePagingSource(apiServices) }
    ).liveData

    private var _latestMovie = MutableLiveData<ResponseLatestMovieDTO>()
    val latestMovie: LiveData<ResponseLatestMovieDTO>
        get() = _latestMovie

    fun getLatestMovie() {

        GlobalScope.launch {

            try {

                val response = apiServices.getLatestMovie()
                if (response.isSuccessful) {
                    _latestMovie.postValue(response.body())
                }
            } catch (e: Exception) {
                Log.i("TAG","getLatestMovie: ${e.message}")
            }
        }
    }
}
