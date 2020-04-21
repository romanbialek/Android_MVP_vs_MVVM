package com.roman.testmvp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.testmvp.R
import com.roman.testmvp.di.component.DaggerActivityComponent
import com.roman.testmvp.di.module.ActivityModule
import com.roman.testmvp.model.Employee
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        presenter.attach(this)
        initView()

    }


    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun initView() {
        presenter.loadEmployees()
    }

    override fun loadDataSuccess(list: List<Employee>) {
        var adapter = EmployeesListAdapter(list.toMutableList())
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setAdapter(adapter)
    }

    override fun showErrorMessage(error: String) {
        layoutError.visibility= View.VISIBLE
        layoutEmpty.visibility= View.GONE
        textViewError.text= error
    }

    private val emptyListObserver= Observer<Boolean> {
        // Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility= View.VISIBLE
        layoutError.visibility= View.GONE
    }
}
