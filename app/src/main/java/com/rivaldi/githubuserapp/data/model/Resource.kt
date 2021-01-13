package com.rivaldi.githubuserapp.data.model


sealed class Resource<T>(
    val data: T? = null,
    val error: Error? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class DataError<T>(error: Error) : Resource<T>(error = error)
}