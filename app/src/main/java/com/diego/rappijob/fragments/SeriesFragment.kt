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
import com.diego.model.Resource
import com.diego.model.Series
import com.diego.rappijob.R
import com.diego.rappijob.adapter.OnItemSelectedListener
import com.diego.rappijob.adapter.SeriesAdapter
import com.diego.rappijob.di.module.ViewModelFactory
import com.diego.rappijob.util.Category
import com.diego.rappijob.util.Utils
import com.diego.rappijob.viewmodel.SeriesViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_items.*
import javax.inject.Inject

class SeriesFragment : Fragment(), OnItemSelectedListener<Series> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: SeriesViewModel
    lateinit var seriesAdapter: SeriesAdapter
    lateinit var snackbar: Snackbar
    lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_items, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[SeriesViewModel::class.java]
        setupView()
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        snackbar = Utils.makeSnackbar(coordinatorLayout, R.string.no_internet)
        viewModel.series.observe(this, Observer { results ->
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
                    results.data?.let { series ->
                        if (series.isNotEmpty()) {
                            textViewEmptyItems.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                            seriesAdapter.updateData(series)
                        }
                    }
                }
            }
        })
    }

    private fun setupView() {
        val array = resources.getStringArray(R.array.array_dropdown)
        spinnerDropdown.adapter = ArrayAdapter(
            requireContext(), R.layout.dropdown_popup_item,
            array.copyOfRange(0, array.size - 1)
        )

        spinnerDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, item: Int, longItem: Long) {
                searchView.onActionViewCollapsed()
                viewModel.loadSeries(
                    if (item == 0) {
                        Category.POPULAR
                    } else {
                        Category.TOP_RATED
                    }
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }

        seriesAdapter = SeriesAdapter(this)
        val manager = GridLayoutManager(requireContext(), 3)
        recyclerView.layoutManager = manager
        recyclerView.adapter = seriesAdapter

        searchView.setOnSearchClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    if (query.length % 3 == 0) {
                        viewModel.searchSeries(query)
                    }
                } else {
                    viewModel.loadSeries(
                        if (spinnerDropdown.selectedItemPosition == 0) {
                            Category.POPULAR
                        } else {
                            Category.TOP_RATED
                        }
                    )
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.searchSeries(query)
                }
                return false
            }
        })
    }

    override fun onItemSelected(item: Series) {
        searchView.clearFocus()
        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        Utils.loadImage(requireContext(), item.posterPath, imageViewPosterBottomSheet)
        textViewTitleBottomSheet.text = item.name
        textViewYearBottomSheet.text = getString(R.string.year_label, item.firstAirDate.subSequence(0, 4))
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