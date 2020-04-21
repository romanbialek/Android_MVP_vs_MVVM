package com.roman.testmvp.di.component

import com.roman.testmvp.di.module.ActivityModule
import com.roman.testmvp.view.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}