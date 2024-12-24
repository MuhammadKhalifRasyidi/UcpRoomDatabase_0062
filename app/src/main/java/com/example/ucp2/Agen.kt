package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependencies.ContainerApp

class Agen: Application() {
    // fungsinya untuk menyimpan instance ContainerApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // membuat instance ContainerApp
        containerApp = ContainerApp(this)
        // instance adalah object yang dibuat dari class
    }
}