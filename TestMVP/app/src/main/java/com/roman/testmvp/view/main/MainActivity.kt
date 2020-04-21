package com.roman.testmvp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.roman.testmvp.R
import com.roman.testmvp.di.component.DaggerActivityComponent
import com.roman.testmvp.di.module.ActivityModule
import com.roman.testmvp.model.Employee
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        presenter.attach(this)
    }


    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDataSuccess(list: List<Employee>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
