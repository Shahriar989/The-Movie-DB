package com.shahriar.a12_the_movie_db.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shahriar.a12_the_movie_db.api.ApiServices
import com.shahriar.a12_the_movie_db.data.dto.upcoming_movie.MovieResult

class UpcomingMoviePagingSource(private val api: ApiServices) : PagingSource<Int, MovieResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        return try {
            val position = params.key ?: 1
            val response = api.getUpcomingMovies("en-US", position)

            LoadResult.Page(
                data = response.movieResults,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.total_pages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
