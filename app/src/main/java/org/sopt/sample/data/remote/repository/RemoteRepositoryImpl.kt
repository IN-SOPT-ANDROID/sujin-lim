package org.sopt.sample.data.remote.repository

import org.sopt.sample.data.remote.model.request.auth.RequestLoginDto
import org.sopt.sample.data.remote.model.request.auth.RequestSignupDto
import org.sopt.sample.data.remote.model.response.auth.ResponseAuthDto
import org.sopt.sample.data.remote.model.response.home.ResponseUserDto
import org.sopt.sample.data.remote.source.RemoteDataSource
import retrofit2.Call
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {
    override suspend fun login(request: RequestLoginDto): ResponseAuthDto =
        remoteDataSource.login(request)

    override suspend fun signup(request: RequestSignupDto): ResponseAuthDto =
        remoteDataSource.signup(request)

    override suspend fun getUsers(page: Int): ResponseUserDto =
        remoteDataSource.getUsers(page)
}