package com.shahriar.a12_the_movie_db.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shahriar.a12_the_movie_db.repo.MovieRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieViewModel @Inject constructor(private var repositories: MovieRepositories) : ViewModel() {

    val upcomingMovies = repositories.getUpcomingMovie().cachedIn(viewModelScope)

    val latestMoviesVMLD = repositories.latestMovie
    fun latestMovieVM() = repositories.getLatestMovie()
}
