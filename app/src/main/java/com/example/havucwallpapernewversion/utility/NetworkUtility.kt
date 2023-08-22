package com.example.havucwallpapernewversion.utility

private fun <T : Any> NetworkResult<T>.mapToResult(): Result<T> {
    return when (this) {
        is NetworkResult.Success -> Result.Success(response)
        is NetworkResult.Error -> Result.Fail(message ?: "")
        is NetworkResult.Exception -> Result.Fail(e.message ?: "")
    }
}

private suspend fun <T : Any, K : Any> Result<T>.map(callback: suspend (T) -> K): Result<K> {
    return when (this) {
        is Result.Success -> {
            Result.Success(callback(this.data))
        }
        is Result.Fail -> Result.Fail(this.message)
    }
}

suspend fun <T : Any, K : Any> NetworkResult<T>.getResult(callback: suspend (T) -> K): Result<K> {
    val result = this.mapToResult()
    return result.map(callback)
}