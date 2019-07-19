package com.diego.local.dao.series

import androidx.room.Dao
import androidx.room.Query
import com.diego.local.dao.BaseDao
import com.diego.model.Series
import java.util.Date

@Dao
abstract class SeriesDao : BaseDao<Series>() {

    @Query("SELECT * FROM Series ORDER BY popularity ASC LIMIT 20")
    abstract suspend fun getSeriesPopular(): List<Series>

    @Query("SELECT * FROM Series ORDER BY voteAverage ASC LIMIT 20")
    abstract suspend fun getSeriesTopRated(): List<Series>

    suspend fun saveSeries(series: List<Series>) {
        insert(series)
    }
}