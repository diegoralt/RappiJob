package com.diego.model

data class Resource<out T>(val status: Status, val data: T?) {
    companion object {

        fun <T> success(data: T?): Resource<T> =
            Resource(Status.SUCCESS, data)

        fun <T> loading(): Resource<T> =
            Resource(Companion.Status.LOADING, null)

        enum class Status {
            SUCCESS,
            LOADING
        }
    }
}