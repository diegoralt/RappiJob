package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewToken(
    @Expose
    @SerializedName("status_code")
    val statusCode: Int? = 0,
    @Expose
    @SerializedName("status_message")
    val statusMessage: String? = "",
    @Expose
    @SerializedName("success")
    val success: Boolean? = false,
    @Expose
    @SerializedName("request_token")
    val requestToken: String? = ""
)