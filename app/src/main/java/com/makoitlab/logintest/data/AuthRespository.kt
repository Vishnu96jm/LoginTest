package com.makoitlab.logintest.data

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.makoitlab.logintest.App

class AuthRepository {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userData = MutableLiveData<FirebaseUser>()
    private val logOutStatus = MutableLiveData<Boolean>()
    private val firebaseUser = firebaseAuth.currentUser

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val saveNoteLiveData = MutableLiveData<Boolean>()
    private val editNoteLiveData = MutableLiveData<Boolean>()


    fun getSaveLiveData(): LiveData<Boolean> = saveNoteLiveData

    fun getEditLiveData(): LiveData<Boolean> = editNoteLiveData

    init {
        if (firebaseAuth.currentUser != null) {
            userData.postValue(firebaseAuth.currentUser)
            logOutStatus.postValue(false)
        }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    userData.postValue(firebaseAuth.currentUser)
                } else {
                    Toast.makeText(
                        App.instance, "Registration failed: ${task.exception!!.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        userData.postValue(firebaseAuth.currentUser)
                    } else {
                        Toast.makeText(
                            App.instance, "Login Failed: " + task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        firebaseAuth.signOut()
                    }
                })
    }

    fun getUser(): MutableLiveData<FirebaseUser> {
        return userData
    }

    fun getLoggedOutData(): MutableLiveData<Boolean> {
        return logOutStatus
    }

    fun logOut() {
        firebaseAuth.signOut()
        logOutStatus.postValue(true)
    }


}