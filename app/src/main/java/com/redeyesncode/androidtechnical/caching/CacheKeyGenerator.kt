package com.redeyesncode.androidtechnical.caching

import java.security.MessageDigest


// Can't store simple string as cache key as we used to do in sharedPreferences.
object CacheKeyGenerator {
    fun generateCacheKey(keyName: String): String {
        val bytes = keyName.toByteArray()
        val digest = MessageDigest.getInstance("MD5").digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }
}