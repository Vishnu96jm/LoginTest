package com.makoitlab.logintest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.makoitlab.logintest.data.AuthRepository

class LogInViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    private var userData = MutableLiveData<FirebaseUser>()
    private var logOutData = MutableLiveData<Boolean>()

    init {
        userData = repository.getUser()
        logOutData = repository.getLoggedOutData()
    }

    fun login(email: String, password: String) {
        repository.login(email, password)
    }

    fun logOut() {
        repository.logOut()
    }

    fun getUserData(): MutableLiveData<FirebaseUser> {
        return userData
    }

}