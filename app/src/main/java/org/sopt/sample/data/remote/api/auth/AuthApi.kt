package org.sopt.sample.data.remote.api.auth

import org.sopt.sample.data.remote.model.request.auth.RequestLoginDto
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDto
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/user/signin")
    suspend fun login(@Body request: RequestLoginDto): ResponseAuthDto

    @POST("api/user/signup")
    suspend fun signup(@Body request: RequestSignupDto): ResponseAuthDto

}