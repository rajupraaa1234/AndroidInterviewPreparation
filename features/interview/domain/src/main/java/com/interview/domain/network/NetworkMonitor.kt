package com.interview.domain.network

import kotlinx.coroutines.flow.Flow


interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}