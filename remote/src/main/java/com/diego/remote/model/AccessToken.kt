package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccessToken(
    @Expose
    @SerializedName("request_token")
    val requestToken: String
)