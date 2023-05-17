package com.makoitlab.logintest

import android.app.Application
import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class App : Application() {

    companion object {
        lateinit var instance: App

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}