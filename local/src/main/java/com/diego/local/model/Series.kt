package com.diego.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Series(
    @PrimaryKey
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String? = "",
    val popularity: Long,
    val voteAverage: Long,
    val firstAirDate: String,
    val originalLanguage: String,
    var lastRefreshed: Date
)