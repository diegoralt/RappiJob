package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Authentication(
    @Expose
    @SerializedName("success")
    val success: Boolean? = false,
    @Expose
    @SerializedName("access_token")
    val accessToken: String? = "",
    @Expose
    @SerializedName("status_code")
    val statusCode: Int? = 0,
    @Expose
    @SerializedName("status_message")
    val statusMessage: String? = "",
    @Expose
    @SerializedName("account_id")
    val accountId: String? = ""
)