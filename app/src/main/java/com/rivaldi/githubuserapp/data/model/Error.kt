package com.rivaldi.githubuserapp.data.model


class Error(val code: Int, val description: String) {

    constructor(exception: Exception) : this(
        code = -1, description = exception.message ?: ""
    )

}