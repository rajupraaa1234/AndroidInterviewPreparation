package com.interview.domain.usecase

import javax.inject.Inject

class SycnSchedulerUseCase @Inject constructor() {
    operator fun invoke() : String {
        return "Scheduler called"
    }
}