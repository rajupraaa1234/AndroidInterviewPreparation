package com.mobile.data.mapper

import com.interview.domain.model.UserInfoResult
import com.interview.domain.model.Users
import com.mobile.data.dto.UsersData

internal fun mapToUserResult(response: List<UsersData>): UserInfoResult = UserInfoResult.Success(
    response.map {
        with(it) {
            Users(
                id = id,
                name = name,
                company = company,
                username = username,
                email = email,
                address = address,
                zip = zip,
                state = state,
                country = country,
                phone = phone,
                photo = photo
            )
        }
    }
)



