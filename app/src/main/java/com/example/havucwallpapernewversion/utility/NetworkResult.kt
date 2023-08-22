package com.example.havucwallpapernewversion.utility

sealed class NetworkResult<T : Any> {
    class Success<T: Any>(val response: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
}