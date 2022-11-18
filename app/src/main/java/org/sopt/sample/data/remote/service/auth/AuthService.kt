package org.sopt.sample.data.remote.service.auth

import org.sopt.sample.data.remote.model.request.auth.RequestLoginDTO
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDTO
import org.sopt.sample.data.remote.model.request.auth.ResponseAuthDTO
import org.sopt.sample.data.remote.model.response.ResponseLoginDTO
import retrofit2.Call
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signin")
    fun login(request: RequestLoginDTO): Call<ResponseAuthDTO>

    @POST("api/user/signup")
    fun signup(request: RequestSignupDTO): Call<ResponseAuthDTO>

}