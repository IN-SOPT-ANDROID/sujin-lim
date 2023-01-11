package org.sopt.sample.data.remote.source

import org.sopt.sample.data.remote.api.auth.AuthApi
import org.sopt.sample.data.remote.api.home.HomeApi
import org.sopt.sample.data.remote.model.request.auth.RequestLoginDto
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDto
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto
import org.sopt.sample.data.remote.model.response.home.ResponseUserDto
import org.sopt.sample.di.RetrofitReqRes
import org.sopt.sample.di.RetrofitSopt
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    @RetrofitSopt private val authApi: AuthApi,
    @RetrofitReqRes private val homeApi: HomeApi
) {
    suspend fun login(request: RequestLoginDto): ResponseAuthDto =
        authApi.login(request)

    suspend fun signup(request: RequestSignupDto): ResponseAuthDto =
        authApi.signup(request)

    suspend fun getUsers(page: Int): ResponseUserDto =
        homeApi.getUsers(page)
}