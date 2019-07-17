package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @Expose
    @SerializedName("popularity")
    val popularity: Long? = 0,
    @Expose
    @SerializedName("vote_count")
    val voteCount: Int? = 0,
    @Expose
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @Expose
    @SerializedName("id")
    val id: Int? = 0,
    @Expose
    @SerializedName("adult")
    val adult: Boolean? = false,
    @Expose
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @Expose
    @SerializedName("title")
    val title: String? = "",
    @Expose
    @SerializedName("vote_average")
    val voteAverage: Long? = 0,
    @Expose
    @SerializedName("overview")
    val overview: String? = "",
    @Expose
    @SerializedName("release_date")
    val releaseDate: String? = ""
)