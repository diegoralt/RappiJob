package com.diego.rappijob.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.domain.series.SeriesUseCases
import com.diego.model.Resource
import com.diego.model.Series
import com.diego.rappijob.BuildConfig
import com.diego.rappijob.util.Category
import com.diego.rappijob.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeriesViewModel @Inject constructor(private val seriesUseCases: SeriesUseCases) : ViewModel() {

    private val _series = MediatorLiveData<Resource<List<Series>>>()
    val series: LiveData<Resource<List<Series>>> get() = _series
    private val key = BuildConfig.API_KEY
    private val language = Utils.getLanguage()

    init {

    }

    fun loadSeries(category: Category) = viewModelScope.launch(Dispatchers.Main) {
        _series.value = Resource.loading()
        val series = async(Dispatchers.IO) {
            if (category == Category.TOP_RATED) {
                seriesUseCases.fetchSeriesTopRated(key, language)
            } else {
                seriesUseCases.fetchSeriesPopular(key, language)
            }
        }
        _series.value = Resource.success(series.await())
    }

    fun searchSeries(query: String) = viewModelScope.launch(Dispatchers.Main) {
        _series.value = Resource.loading()
        val series = async(Dispatchers.IO) {
            seriesUseCases.searchSeries(key, language, query)
        }
        _series.value = Resource.success(series.await())
    }
}