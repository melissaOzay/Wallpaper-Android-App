package com.example.havucwallpapernewversion.utility

sealed class Resource<T> {
    data class Success<T : Any>(val data: T) : Resource<T>()
    data class Fail<T:Any>(val message: String) : Resource<T>()
}
