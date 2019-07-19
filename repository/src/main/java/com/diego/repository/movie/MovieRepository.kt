package com.diego.repository.movie

import com.diego.local.dao.movie.MovieDao
import com.diego.model.Movie
import com.diego.remote.model.ApiResult
import com.diego.remote.movie.MovieRemote
import com.diego.repository.util.DataDelivery
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemote: MovieRemote,
    private val movieDao: MovieDao
) {

    suspend fun fetchMoviesPopular(key: String, language: String): List<Movie> =
        object : DataDelivery<Response<ApiResult<Movie>>, List<Movie>>() {
            override suspend fun processResponse(response: Response<ApiResult<Movie>>): List<Movie> {
                return if (response.isSuccessful) {
                    response.body()?.results ?: loadFromLocal()
                } else {
                    loadFromLocal()
                }
            }

            override suspend fun saveCallResults(items: List<Movie>) {
                movieDao.saveMovie(items)
            }

            override suspend fun loadFromLocal(): List<Movie> =
                movieDao.getMoviesPopular()

            override suspend fun createCallAsync(): Response<ApiResult<Movie>> =
                movieRemote.fetchMoviesPopular(key, language)
        }.start()

    suspend fun fetchMoviesTopRated(key: String, language: String): List<Movie> =
        object : DataDelivery<Response<ApiResult<Movie>>, List<Movie>>() {
            override suspend fun processResponse(response: Response<ApiResult<Movie>>): List<Movie> {
                return if (response.isSuccessful) {
                    response.body()?.results ?: loadFromLocal()
                } else {
                    loadFromLocal()
                }
            }

            override suspend fun saveCallResults(items: List<Movie>) {
                movieDao.saveMovie(items)
            }

            override suspend fun loadFromLocal(): List<Movie> =
                movieDao.getMoviesPopular()

            override suspend fun createCallAsync(): Response<ApiResult<Movie>> =
                movieRemote.fetchMoviesTopRated(key, language)
        }.start()

    suspend fun fetchMoviesUpcoming(key: String, language: String): List<Movie> =
        object : DataDelivery<Response<ApiResult<Movie>>, List<Movie>>() {
            override suspend fun processResponse(response: Response<ApiResult<Movie>>): List<Movie> {
                return if (response.isSuccessful) {
                    response.body()?.results ?: loadFromLocal()
                } else {
                    loadFromLocal()
                }
            }

            override suspend fun saveCallResults(items: List<Movie>) {
                movieDao.saveMovie(items)
            }

            override suspend fun loadFromLocal(): List<Movie> =
                movieDao.getMoviesUpcoming()

            override suspend fun createCallAsync(): Response<ApiResult<Movie>> =
                movieRemote.fetchMoviesUpcoming(key, language)
        }.start()
}