package org.sopt.sample.data.remote.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.sopt.sample.data.remote.api.ApiFactory.reqresRetrofit
import org.sopt.sample.data.remote.api.ApiFactory.retrofit
import org.sopt.sample.data.remote.service.auth.AuthService
import org.sopt.sample.data.remote.service.home.HomeService
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL = "http://3.39.169.52:3000/"
    private const val REQRES_BASE_URL = "https://reqres.in/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(retrofit: Retrofit): T = retrofit.create<T>(T::class.java)
}

object ApiPool {
    val authApi = ApiFactory.create<AuthService>(retrofit)
    val homeApi = ApiFactory.create<HomeService>(reqresRetrofit)
}