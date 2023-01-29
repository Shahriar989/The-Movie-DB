package com.shahriar.a12_the_movie_db.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.shahriar.a12_the_movie_db.R
import com.shahriar.a12_the_movie_db.databinding.ActivityMainBinding
import com.shahriar.a12_the_movie_db.utils.bottomNavSetKoro
import com.shahriar.a12_the_movie_db.utils.navControllerDe
import com.shahriar.a12_the_movie_db.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (this::binding.isInitialized) {
            setContentView(binding.root)
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }

        toast(this, "Hello")

        navController = navControllerDe(R.id.fragmentContainerView)

        binding.bottomNavView.bottomNavSetKoro(navController)
    }
}
