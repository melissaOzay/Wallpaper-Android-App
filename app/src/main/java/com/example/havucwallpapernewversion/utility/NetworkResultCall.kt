package com.example.havucwallpapernewversion.utility

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

internal class NetworkResultCall<S : Any>(
    private val delegate: Call<S>,
) : Call<NetworkResult<S>> {


    override fun enqueue(callback: Callback<NetworkResult<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResultCall,
                            Response.success(NetworkResult.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@NetworkResultCall,
                            Response.success(NetworkResult.Exception(Throwable()))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            ""
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResultCall,
                            Response.success(NetworkResult.Error<S>(code, errorBody))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResultCall,
                            Response.success(NetworkResult.Exception(Throwable()))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResult.Exception<S>(throwable)
                    else -> NetworkResult.Exception(throwable)
                }
                callback.onResponse(this@NetworkResultCall, Response.success(networkResponse))
            }


        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResultCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResult<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }


    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = Timeout()
}