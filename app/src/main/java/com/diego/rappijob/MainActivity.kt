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
    private var itemActual = R.id.action_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        navController = findNavController(R.id.navHostFragment)
        navigationBottom.setOnNavigationItemSelectedListener { itemSelected ->
            if (itemSelected.itemId != itemActual) {
                itemActual = itemSelected.itemId
                if (itemSelected.itemId == R.id.action_movies) {
                    navController.navigate(SeriesFragmentDirections.destinationMovie())
                } else {
                    navController.navigate(MoviesFragmentDirections.destinationSeries())
                }
            }
            false
        }
    }
}
