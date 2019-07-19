package com.diego.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Series(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("popularity")
    val popularity: Long,
    @SerializedName("vote_average")
    val voteAverage: Long,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("original_language")
    val originalLanguage: String
)