package com.rivaldi.githubuserapp.data.remote.service

import com.rivaldi.githubuserapp.data.model.SearchResponse
import com.rivaldi.githubuserapp.data.model.User
import com.rivaldi.githubuserapp.data.model.UserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    suspend fun search(
        @Query("q") username: String
    ): Response<SearchResponse>

    @GET("users/{username}")
    suspend fun detail(
        @Path("username") username: String
    ): Response<UserDetail>
}