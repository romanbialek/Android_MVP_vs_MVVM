package com.roman.testmvp.di.module

import android.app.Application
import com.roman.testmvp.BaseApp
import com.roman.testmvp.di.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}