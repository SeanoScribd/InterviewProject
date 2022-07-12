package com.sean.oxford.scribdproject.persistence

sealed class CacheResult<T> {

    data class Success<T>(val data: T): CacheResult<T>()

    data class Error<T>(val error: String?): CacheResult<T>()

}