package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Series(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("popularity")
    val popularity: Long,
    @Expose
    @SerializedName("vote_count")
    val voteCount: Int,
    @Expose
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @Expose
    @SerializedName("original_language")
    val originalLanguage: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("vote_average")
    val voteAverage: Long,
    @Expose
    @SerializedName("overview")
    val overview: String,
    @Expose
    @SerializedName("poster_path")
    val posterPath: String? = ""
)