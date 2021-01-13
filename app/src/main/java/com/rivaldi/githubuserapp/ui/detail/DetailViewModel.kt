package com.rivaldi.githubuserapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldi.githubuserapp.data.model.User
import com.rivaldi.githubuserapp.data.model.UserDetail
import com.rivaldi.githubuserapp.data.repository.UserRepository
import com.rivaldi.githubuserapp.util.Coroutines
import com.rivaldi.githubuserapp.util.ext.post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject
constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val user: LiveData<User> = MutableLiveData()
    val userDetail: LiveData<UserDetail> = MutableLiveData()
    val userDetailResponse = userRepository.userDetailResponse

    fun setUser(usersItem: User) = Coroutines.io {
        user.post(usersItem)
    }

    fun setUserDetail(usersItem: UserDetail) {
        userDetail.post(usersItem)
    }

    fun getDetail(username: String) = CoroutineScope(Dispatchers.IO).launch {
        launch {
            userRepository.detail(username)
        }
    }

}