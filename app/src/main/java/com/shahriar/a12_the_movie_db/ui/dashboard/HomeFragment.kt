package com.shahriar.a12_the_movie_db.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.shahriar.a12_the_movie_db.R
import com.shahriar.a12_the_movie_db.databinding.FragmentHomeBinding
import com.shahriar.a12_the_movie_db.ui.UpcomingMovieViewModel
import com.shahriar.a12_the_movie_db.ui.adapter.UpcomingMovieAdapter
import com.shahriar.a12_the_movie_db.utils.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: UpcomingMovieAdapter

    private val viewModel by viewModels<UpcomingMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel.latestMovieVM()

        return if (this::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UpcomingMovieAdapter()

        binding.rv.adapter = adapter

        binding.latestMovieVP.adapter = adapter

        lifecycleScope.launch {

            (adapter as PagingDataAdapter<*, *>).loadStateFlow.collectLatest { loadState ->

                when (loadState.refresh) {

                    is LoadState.Loading -> {
                        Log.i("TAG", "Loading......")
                    }

                    is LoadState.Error -> {
                        bind(loadState.refresh)
                    }

                    is LoadState.NotLoading -> {
                        Log.i("TAG", "Not Loading......")
                    }
                }
            }
        }

        viewModel.upcomingMovies.observe(requireActivity()) {

            adapter.submitData(lifecycle, it)
        }

        viewModel.latestMoviesVMLD.observe(viewLifecycleOwner) {

            Glide.with(requireActivity()).load(Util.posterUrlMake(it.poster_path))
                .placeholder(R.drawable.placeholder)
                .into(binding.latestMovie)
        }
    }

    private fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            val errMessage = loadState.error.localizedMessage
            Log.i("TAG", "$errMessage ")
        }
    }
}
