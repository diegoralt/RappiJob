package com.diego.domain.movie

import com.diego.repository.movie.MovieRepository
import javax.inject.Inject

class MovieUseCases @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun fetchMoviesPopular(key: String, language: String) =
        movieRepository.fetchMoviesPopular(key, language)

    suspend fun fetchMoviesTopRated(key: String, language: String) =
        movieRepository.fetchMoviesTopRated(key, language)

    suspend fun fetchMoviesUpcoming(key: String, language: String) =
        movieRepository.fetchMoviesUpcoming(key, language)

}