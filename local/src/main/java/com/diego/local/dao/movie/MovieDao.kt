package com.diego.local.dao.movie

import androidx.room.Dao
import androidx.room.Query
import com.diego.local.dao.BaseDao
import com.diego.model.Movie
import java.util.Date

@Dao
abstract class MovieDao : BaseDao<Movie>() {

    @Query("SELECT * FROM Movie ORDER BY popularity ASC LIMIT 30")
    abstract suspend fun getMoviesPopular(): List<Movie>

    @Query("SELECT * FROM Movie ORDER BY voteAverage ASC LIMIT 30")
    abstract suspend fun getMoviesTopRated(): List<Movie>

    @Query("SELECT * FROM Movie LIMIT 30")
    abstract suspend fun getMoviesUpcoming(): List<Movie>

    suspend fun saveMovie(movies: List<Movie>) {
        insert(movies)
    }
}