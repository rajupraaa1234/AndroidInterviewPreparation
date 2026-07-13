package com.mobile.data.mapper

import com.interview.domain.model.Users
import com.mobile.data.local.entity.UserEntity

internal fun List<UserEntity>.toDomain() = with(this) {
    map {
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
}

internal fun List<Users>.toEntity() = with(this) {
    map {
        with(it) {
            UserEntity(
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
}



