package com.diego.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diego.local.dao.movie.MovieDao
import com.diego.local.dao.series.SeriesDao
import com.diego.local.model.Movie
import com.diego.local.model.Series

@Database(entities = [Movie::class, Series::class], version = 1, exportSchema = false)
abstract class RappiJobDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun seriesDao(): SeriesDao

    companion object {
        @Volatile
        private var INSTANCE: RappiJobDatabase? = null

        fun getInstance(context: Context): RappiJobDatabase {
            return INSTANCE ?: synchronized(this) {
                buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, RappiJobDatabase::class.java, "RappiJob.db")
                .build()

    }
}