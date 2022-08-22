package com.owow.characterapp.data.service.network

import com.google.gson.GsonBuilder
import com.owow.characterapp.BuildConfig
import com.owow.characterapp.utills.CommonUtils.CONNECT_TIMEOUT
import com.owow.characterapp.utills.CommonUtils.READ_TIMEOUT
import com.owow.characterapp.utills.CommonUtils.WRITE_TIMEOUT
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientBuilder(private val interceptor: ConnectionInterceptor) :
    KoinComponent {
    fun getRetrofit() = retrofit
    private val httpLoggingInterceptor = HttpLoggingInterceptor()
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    private val builder = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))

    private fun createClientAuth(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    private val retrofit: Retrofit = builder.client(createClientAuth()).build()

    init {
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
    }
}
