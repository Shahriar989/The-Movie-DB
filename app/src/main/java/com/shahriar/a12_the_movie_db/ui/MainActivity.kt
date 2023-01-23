package com.shahriar.a12_the_movie_db.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shahriar.a12_the_movie_db.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (this::binding.isInitialized) {
            setContentView(binding.root)
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
    }
}
