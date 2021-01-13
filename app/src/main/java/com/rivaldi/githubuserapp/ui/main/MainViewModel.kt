package com.rivaldi.githubuserapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldi.githubuserapp.data.repository.UserRepository
import com.rivaldi.githubuserapp.util.Coroutines
import com.rivaldi.githubuserapp.util.ext.post
import javax.inject.Inject

class MainViewModel @Inject
constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val user = userRepository.searchResponse
    val searchText: LiveData<String> = MutableLiveData()

    fun searchUser(username: String) = Coroutines.io {
        searchText.post(username)
        userRepository.search(username)
    }

}