package com.mobile.core_cache.cache

interface MemoryCache {

    fun <T : Any> get(key: String): T?

    fun put(
        key: String,
        value: Any
    )

    fun remove(key: String)

    fun clear()
}