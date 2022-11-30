package org.sopt.sample.data.remote.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.sample.data.remote.api.ApiFactory.reqresRetrofit
import org.sopt.sample.data.remote.api.ApiFactory.retrofit
import org.sopt.sample.data.remote.api.auth.AuthApi
import org.sopt.sample.data.remote.api.home.HomeApi
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL = "http://3.39.169.52:3000/"
    private const val REQRES_BASE_URL = "https://reqres.in/"

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }

    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }

    inline fun <reified T> create(retrofit: Retrofit): T = retrofit.create<T>(T::class.java)
}

object ApiPool {
    val authApi = ApiFactory.create<AuthApi>(retrofit)
    val homeApi = ApiFactory.create<HomeApi>(reqresRetrofit)
}