package org.sopt.sample.data.remote.service.home

import org.sopt.sample.data.remote.model.response.home.ResponseUserDTO
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeService {
    @POST("api/users")
    fun getUsers(@Query("page") page: Int): Call<ResponseUserDTO>
}