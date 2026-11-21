package com.interview.domain.usecase

import com.interview.domain.repository.UsersRepository
import javax.inject.Inject


class GetUserUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke() = userRepository.getUsers()
}