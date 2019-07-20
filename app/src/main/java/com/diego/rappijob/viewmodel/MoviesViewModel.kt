package com.diego.rappijob.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.domain.movie.MovieUseCases
import com.diego.model.Movie
import com.diego.model.Resource
import com.diego.rappijob.BuildConfig
import com.diego.rappijob.util.Category
import com.diego.rappijob.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val movieUseCases: MovieUseCases) : ViewModel() {

    private val _movies = MediatorLiveData<Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>> get() = _movies

    init {
        loadMovies(Category.POPULAR)
    }

    fun loadMovies(category: Category) {
        viewModelScope.launch(Dispatchers.Main) {
            _movies.value = Resource.loading()
            val movies = async(Dispatchers.IO) {
                val key = BuildConfig.API_KEY
                val language = Utils.getLanguage()
                when (category) {
                    Category.POPULAR -> movieUseCases.fetchMoviesPopular(key, language)
                    Category.TOP_RATED -> movieUseCases.fetchMoviesTopRated(key, language)
                    Category.UPCOMING -> movieUseCases.fetchMoviesUpcoming(key, language)
                }
            }
            _movies.value = Resource.success(movies.await())
        }
    }
}