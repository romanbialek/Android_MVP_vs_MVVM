package com.roman.testmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.testmvvm.R
import com.roman.testmvvm.di.Injection
import com.roman.testmvvm.model.Employee
import com.roman.testmvvm.viewmodel.EmployeesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeesViewModel
    private lateinit var adapter: EmployeesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
    }

    //ui
    private fun setupUI(){
        adapter= EmployeesListAdapter(viewModel.employees.value?: emptyList())
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }

    //viewmodel
    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(EmployeesViewModel::class.java)
        viewModel.employees.observe(this,renderEmployees)
        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.isEmptyList.observe(this,emptyListObserver)
    }

    //observers
    private val renderEmployees= Observer<List<Employee>> {
        Log.v("Console -- ", "data updated $it")
        layoutError.visibility= View.GONE
        layoutEmpty.visibility= View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver= Observer<Boolean> {
        val visibility=if(it) View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        layoutError.visibility= View.VISIBLE
        layoutEmpty.visibility= View.GONE
        textViewError.text= "Error $it"
    }

    private val emptyListObserver= Observer<Boolean> {
        layoutEmpty.visibility= View.VISIBLE
        layoutError.visibility= View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadEmployees()
    }
}
