package com.diego.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String? = "",
    val popularity: Long,
    val voteAverage: Long,
    val releaseDate: String,
    val originalLanguage: String,
    var lastRefreshed: Date
)