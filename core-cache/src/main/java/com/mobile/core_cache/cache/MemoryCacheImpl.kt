package com.mobile.core_cache.cache

import jakarta.inject.Singleton
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
@Singleton
class MemoryCacheImpl @Inject constructor() : MemoryCache {

    private val cache = ConcurrentHashMap<String, Any>()
    // Differences b/w HashMap & ConcurrentHashMap is ConcurrentHashMap - thread-safe (multiple can read/write at a time)

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(key: String): T? {
        return cache[key] as? T
    }

    override fun put(
        key: String,
        value: Any
    ) {
        cache[key] = value
    }

    override fun remove(key: String) {
        cache.remove(key)
    }

    override fun clear() {
        cache.clear()
    }
}