package com.rivaldi.githubuserapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivaldi.githubuserapp.BuildConfig
import com.rivaldi.githubuserapp.R
import com.rivaldi.githubuserapp.data.local.UserDao
import com.rivaldi.githubuserapp.data.model.Error
import com.rivaldi.githubuserapp.data.model.Resource
import com.rivaldi.githubuserapp.data.model.User
import com.rivaldi.githubuserapp.data.model.UserDetail
import com.rivaldi.githubuserapp.data.remote.config.SafeApiRequest
import com.rivaldi.githubuserapp.data.remote.config.ServiceGenerator
import com.rivaldi.githubuserapp.data.remote.service.UserService
import com.rivaldi.githubuserapp.util.ext.post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject
constructor(
    serviceGenerator: ServiceGenerator,
    private val context: Context,
    private val userDao: UserDao
) : SafeApiRequest() {

    val searchResponse: LiveData<Resource<List<User>>> = MutableLiveData()
    val userDetailResponse: LiveData<Resource<UserDetail>> = MutableLiveData()

    private val service = serviceGenerator.createService(
        UserService::class.java,
        BuildConfig.BASE_URL
    )

    suspend fun search(username: String) = withContext(Dispatchers.IO) {
        searchResponse.post(Resource.Loading())
        try {
            val response = apiRequest {
                service.search(username)
            }

            if (response.items.isNotEmpty()) {
                searchResponse.post(Resource.Success(response.items))
            } else {
                val message = context.getString(R.string.no_data)
                searchResponse.post(Resource.DataError(Error(0, message)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            searchResponse.post(Resource.DataError(Error(e)))
        }
    }

    suspend fun detail(username: String) = withContext(Dispatchers.IO) {
        userDetailResponse.post(Resource.Loading())
        try {
            val response = apiRequest {
                service.detail(username)
            }
            userDetailResponse.post(Resource.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            userDetailResponse.post(Resource.DataError(Error(e)))
        }
    }

}