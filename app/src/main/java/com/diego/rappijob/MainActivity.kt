package com.diego.rappijob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.diego.rappijob.fragments.MoviesFragmentDirections
import com.diego.rappijob.fragments.SeriesFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        navController = findNavController(R.id.navHostFragment)
        navigationBottom.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.action_movies && navController.currentDestination != SeriesFragmentDirections.destinationMovie()) {
                navController.navigate(SeriesFragmentDirections.destinationMovie())
            } else if (navController.currentDestination != MoviesFragmentDirections.destinationSeries()) {
                navController.navigate(MoviesFragmentDirections.destinationSeries())
            }
            true
        }
    }
}
