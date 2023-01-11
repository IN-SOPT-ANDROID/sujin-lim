package org.sopt.sample.data.remote.model.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    val email: String,
    val password: String,
)

@Serializable
data class RequestSignupDto(
    val email: String,
    val password: String,
    val name: String,
)