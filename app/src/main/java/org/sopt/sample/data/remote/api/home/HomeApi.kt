package org.sopt.sample.data.remote.api.home

import org.sopt.sample.data.remote.model.response.home.ResponseUserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): ResponseUserDto
}