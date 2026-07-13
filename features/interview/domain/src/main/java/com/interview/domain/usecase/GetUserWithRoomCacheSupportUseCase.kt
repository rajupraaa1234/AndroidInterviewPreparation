package com.interview.domain.usecase

import com.interview.domain.repository.UserRepositoryWithRoom
import javax.inject.Inject

class GetUserWithRoomCacheSupportUseCase @Inject constructor(
    private val repository: UserRepositoryWithRoom
) {
    suspend operator fun invoke() = repository.getUsers()
}