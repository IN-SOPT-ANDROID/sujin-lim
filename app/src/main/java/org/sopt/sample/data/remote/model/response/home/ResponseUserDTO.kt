package org.sopt.sample.data.remote.model.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDTO(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_page")
    val totalPage: Int,
    val data: List<UserDTO>
)

@Serializable
data class UserDTO(
    val id: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val LastName: String,
    val avatar: String,
)

