package com.roman.testmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roman.testmvvm.model.EmployeesDataSource

class ViewModelFactory(private val repository: EmployeesDataSource): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmployeesViewModel(repository) as T
    }
}