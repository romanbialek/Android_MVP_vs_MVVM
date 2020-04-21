package com.roman.testmvp

import android.annotation.SuppressLint
import android.app.Application
import com.roman.testmvp.di.component.ApplicationComponent
import com.roman.testmvp.di.component.DaggerApplicationComponent
import com.roman.testmvp.di.module.ApplicationModule

@SuppressLint("Registered")
class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}