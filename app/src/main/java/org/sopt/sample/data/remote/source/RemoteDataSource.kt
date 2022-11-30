package org.sopt.sample.data.remote.source

import org.sopt.sample.data.remote.model.request.auth.RequestLoginDto
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDto
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto
import org.sopt.sample.data.remote.model.response.home.ResponseUserDto

interface RemoteDataSource {
    // Sopt
    suspend fun login(request: RequestLoginDto): ResponseAuthDto
    suspend fun signup(request: RequestSignupDto): ResponseAuthDto

    // ReqRes
    suspend fun getUsers(page: Int): ResponseUserDto
}