package org.sopt.sample.data.remote.model.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAuthDto(
    val status: Int,
    val message: String,
    @SerialName("newUser")
    val result: Result
)

@Serializable
data class Result(
    val id: Int,
    val name: String,
    val profileImage: String?,
    val bio: String?,
    val email: String,
    val password: String
)

