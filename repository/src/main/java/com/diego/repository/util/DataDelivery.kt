package com.diego.repository.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

abstract class DataDelivery<ResponseType, ResultType> {

    suspend fun start(): ResultType =
        try {
            fetchFromNetwork()
        } catch (e: Exception) {
            loadFromLocal()
        }

    private suspend fun fetchFromNetwork(): ResultType =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val apiResponse = async { createCallAsync() }
            val resultType = async { processResponse(apiResponse.await()) }
            resultType.await()?.also {
                saveCallResults(it)
            } ?: run {
                loadFromLocal()
            }
        }

    protected abstract suspend fun processResponse(response: ResponseType): ResultType?

    protected abstract suspend fun saveCallResults(items: ResultType)

    protected abstract suspend fun loadFromLocal(): ResultType

    protected abstract suspend fun createCallAsync(): ResponseType
}