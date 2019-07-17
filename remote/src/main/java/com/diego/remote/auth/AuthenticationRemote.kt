package com.diego.remote.auth

import com.diego.remote.Service
import com.diego.remote.model.AccessToken
import com.diego.remote.model.RequestToken
import javax.inject.Inject

class AuthenticationRemote @Inject constructor(private val service: Service) {

    suspend fun requestToken(headers: HashMap<String, String>, params: RequestToken) =
        service.requestToken(headers, params)

    suspend fun accessToken(headers: HashMap<String, String>, params: AccessToken) =
        service.accessToken(headers, params)

}