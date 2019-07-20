package com.diego.rappijob.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.diego.model.Movie
import com.diego.model.Resource
import com.diego.rappijob.R
import com.diego.rappijob.adapter.MovieAdapter
import com.diego.rappijob.adapter.OnItemSelectedListener
import com.diego.rappijob.di.module.ViewModelFactory
import com.diego.rappijob.util.Category
import com.diego.rappijob.util.Utils
import com.diego.rappijob.viewmodel.MoviesViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_items.*
import javax.inject.Inject

class MoviesFragment : Fragment(), OnItemSelectedListener<Movie> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MoviesViewModel
    lateinit var movieAdapter: MovieAdapter
    lateinit var snackbar: Snackbar
    lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_items, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[MoviesViewModel::class.java]
        setupView()
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        snackbar = Utils.makeSnackbar(coordinatorLayout, R.string.no_internet)
        viewModel.movies.observe(this, Observer { results ->
            when (results.status) {
                Resource.Companion.Status.LOADING -> {
                    validateNetwork()
                    sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    textViewEmptyItems.visibility = View.GONE
                }
                Resource.Companion.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    textViewEmptyItems.visibility = View.VISIBLE
                    results.data?.let { movies ->
                        if (movies.isNotEmpty()) {
                            textViewEmptyItems.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                            movieAdapter.updateData(movies)
                        }
                    }
                }
            }
        })
    }

    private fun setupView() {
        spinnerDropdown.adapter = ArrayAdapter(
            requireContext(), R.layout.dropdown_popup_item,
            resources.getStringArray(R.array.array_dropdown)
        )

        spinnerDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, item: Int, longItem: Long) {
                searchView.onActionViewCollapsed()
                viewModel.loadMovies(
                    when (item) {
                        1 -> Category.TOP_RATED
                        2 -> Category.UPCOMING
                        else -> Category.POPULAR
                    }
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }

        movieAdapter = MovieAdapter(this)
        val manager = GridLayoutManager(requireContext(), 3)
        recyclerView.layoutManager = manager
        recyclerView.adapter = movieAdapter

        searchView.setOnSearchClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    if (query.length % 3 == 0) {
                        viewModel.searchMovies(query)
                    }
                } else {
                    viewModel.loadMovies(
                        when (spinnerDropdown.selectedItemPosition) {
                            1 -> Category.TOP_RATED
                            2 -> Category.UPCOMING
                            else -> Category.POPULAR
                        }
                    )
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.searchMovies(query)
                }
                return false
            }
        })
    }

    override fun onItemSelected(item: Movie) {
        searchView.clearFocus()
        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        Utils.loadImage(requireContext(), item.posterPath, imageViewPosterBottomSheet)
        textViewTitleBottomSheet.text = item.title
        textViewYearBottomSheet.text = getString(R.string.year_label, item.releaseDate.subSequence(0, 4))
        textViewPopulatiryBottomSheet.text = getString(R.string.popularity_label, item.popularity)
        textViewAverageBottomSheet.text = getString(R.string.average_label, item.voteAverage)
        textViewOverviewBottomSheet.text = getString(R.string.overview_label, item.overview)
    }

    private fun validateNetwork() {
        if (!Utils.isNetworkOnline(requireContext())) {
            snackbar.show()
        } else {
            snackbar.dismiss()
        }
    }
}