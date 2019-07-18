package com.diego.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.diego.local.model.Movie
import java.util.Date

@Dao
abstract class MovieDao : BaseDao<Movie>() {

    @Query("SELECT * FROM Movie ORDER BY popularity ASC LIMIT 20")
    abstract suspend fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM Movie ORDER BY voteAverage ASC LIMIT 20")
    abstract suspend fun getTopRatedMovies(): List<Movie>

    suspend fun saveMovie(movies: List<Movie>) {
        insert(movies.apply { forEach { it.lastRefreshed = Date() } })
    }
}