package com.roman.testmvp.di.component

import com.roman.testmvp.BaseApp
import com.roman.testmvp.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: BaseApp)
}