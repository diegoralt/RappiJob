package com.diego.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.diego.local.model.Series
import java.util.Date

@Dao
abstract class SeriesDao : BaseDao<Series>() {

    @Query("SELECT * FROM Series ORDER BY popularity ASC LIMIT 20")
    abstract suspend fun getPopularSeries(): List<Series>

    @Query("SELECT * FROM Series ORDER BY voteAverage ASC LIMIT 20")
    abstract suspend fun getTopRatedSeries(): List<Series>

    suspend fun saveSeries(series: List<Series>) {
        insert(series.apply { forEach { it.lastRefreshed = Date() } })
    }
}