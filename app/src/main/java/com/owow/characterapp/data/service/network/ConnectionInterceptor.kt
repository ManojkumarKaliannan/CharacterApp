package com.owow.characterapp.data.service.network

import com.owow.characterapp.utills.CommonUtils
import com.owow.characterapp.utills.ConnectionService
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import java.io.IOException

class ConnectionInterceptor(private val connectionService: ConnectionService) : Interceptor,
    KoinComponent {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectionService.isOnline()) {
            // Throwing our custom exception 'NoConnectivityException'
            throw NoConnectivityException()
        }
        val newRequest = chain.request().newBuilder()
            .header(CommonUtils.ACCEPT_KEY, CommonUtils.APPLICATION_JSON)
            .build()
        return chain.proceed(newRequest)
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}

