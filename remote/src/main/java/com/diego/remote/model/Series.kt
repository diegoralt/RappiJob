package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Series(
    @Expose
    @SerializedName("name")
    val name: String? = "",
    @Expose
    @SerializedName("popularity")
    val popularity: Long? = 0,
    @Expose
    @SerializedName("vote_count")
    val voteCount: Int? = 0,
    @Expose
    @SerializedName("first_air_date")
    val firstAirDate: String? = "",
    @Expose
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @Expose
    @SerializedName("id")
    val id: Int? = 0,
    @Expose
    @SerializedName("vote_average")
    val voteAverage: Long? = 0,
    @Expose
    @SerializedName("overview")
    val overview: String? = "",
    @Expose
    @SerializedName("poster_path")
    val posterPath: String? = ""
)