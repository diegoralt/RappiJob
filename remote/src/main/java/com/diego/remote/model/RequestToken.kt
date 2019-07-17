package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestToken(
    @Expose
    @SerializedName("redirect_to")
    val redirect_to: String
)

const val DEFAULT_REDIRECT_TO = "http://www.themoviedb.org/"