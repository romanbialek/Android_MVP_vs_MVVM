package com.roman.testmvp.di.module

import android.app.Activity
import com.roman.testmvp.data.ApiClientInterface
import com.roman.testmvp.view.main.MainContract
import com.roman.testmvp.view.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Provides
    fun provideApiService(): ApiClientInterface {
        return ApiClientInterface.create()
    }
}

