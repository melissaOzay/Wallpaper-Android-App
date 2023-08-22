package com.example.havucwallpapernewversion.utility

sealed class Result<T> {
    data class Success<T : Any>(val data: T) : Result<T>()
    data class Fail<T:Any>(val message: String) : Result<T>()
}
