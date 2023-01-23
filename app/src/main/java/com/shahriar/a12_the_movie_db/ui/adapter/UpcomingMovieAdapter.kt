package com.shahriar.a12_the_movie_db.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahriar.a12_the_movie_db.data.dto.upcoming_movie.MovieResult
import com.shahriar.a12_the_movie_db.databinding.ItemMovieBinding
import com.shahriar.a12_the_movie_db.utils.Util

class UpcomingMovieAdapter :
    PagingDataAdapter<MovieResult, UpcomingMovieAdapter.UpcomingMovieViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
        getItem(position).let {

            Glide.with(holder.binding.root.context)
                .load(Util.posterUrlMake(it?.poster_path))
                .into(holder.binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {

        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UpcomingMovieViewHolder(binding)
    }

    class UpcomingMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val Comparator =
            object : DiffUtil.ItemCallback<MovieResult>() {
                override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {

                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieResult,
                    newItem: MovieResult
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
