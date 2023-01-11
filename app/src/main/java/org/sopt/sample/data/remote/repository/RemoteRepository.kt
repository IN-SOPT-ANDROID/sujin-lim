package org.sopt.sample.data.remote.repository

import org.sopt.sample.data.remote.model.request.auth.RequestLoginDto
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDto
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto
import org.sopt.sample.data.remote.model.response.home.ResponseUserDto
import retrofit2.Call

interface RemoteRepository {
    // Sopt
    suspend fun login(request: RequestLoginDto): ResponseAuthDto
    suspend fun signup(request: RequestSignupDto): ResponseAuthDto

    // ReqRes
    suspend fun getUsers(page: Int): ResponseUserDto
}