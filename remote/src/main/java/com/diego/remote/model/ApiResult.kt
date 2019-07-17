package com.diego.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResult<T>(
    @Expose
    @SerializedName("page")
    val page: Int? = 0,
    @Expose
    @SerializedName("total_results")
    val totalResults: Int? = 0,
    @Expose
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @Expose
    @SerializedName("results")
    val results: List<T>
)