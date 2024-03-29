package com.shahriar.a12_the_movie_db.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationBarView

public fun NavigationBarView.bottomNavSetKoro(navController: NavController) {
    NavigationUI.setupWithNavController(this, navController)
}

public fun Activity.navControllerDe(fragId: Int): NavController {
    return Navigation.findNavController(this, fragId)
}

public fun Any.toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}
