package com.diego.repository.util

abstract class DataDelivery<ResponseType, ResultType> {

    suspend fun start(): ResultType =
        try {
            fetchFromNetwork()
        } catch (e: Exception) {
            loadFromLocal()
        }

    private suspend fun fetchFromNetwork(): ResultType {
        val apiResponse = createCallAsync()
        val resultType = processResponse(apiResponse)
        saveCallResults(resultType)
        return resultType
    }

    protected abstract suspend fun processResponse(response: ResponseType): ResultType

    protected abstract suspend fun saveCallResults(items: ResultType)

    protected abstract suspend fun loadFromLocal(): ResultType

    protected abstract suspend fun createCallAsync(): ResponseType
}