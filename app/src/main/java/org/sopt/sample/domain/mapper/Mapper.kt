package org.sopt.sample.domain.mapper

import org.sopt.sample.data.remote.model.response.home.UserDto
import org.sopt.sample.domain.model.home.User

object Mapper {
    fun getUsersMapper(response: List<UserDto>): List<User> {
        val users = if (response.isEmpty()) {
            listOf<User>()
        } else {
            response.map {
                User(
                    id = it.id,
                    name = it.firstName,
                    email = it.email,
                    profileUrl = it.avatar
                )
            }
        }
        return users
    }
}