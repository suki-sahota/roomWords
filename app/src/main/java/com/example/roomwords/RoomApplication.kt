package com.example.roomwords

import android.app.Application
import android.content.Context

class RoomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        roomContext = applicationContext
    }

    companion object {
        var roomContext: Context? = null
    }
}