package com.roman.testmvvm.di

import androidx.lifecycle.ViewModelProvider
import com.roman.testmvvm.model.EmployeesDataSource
import com.roman.testmvvm.model.EmployeesRepository
import com.roman.testmvvm.viewmodel.ViewModelFactory

object Injection {

    private val employeesDataSource: EmployeesDataSource = EmployeesRepository()
    private val employeesViewModelFactory = ViewModelFactory(employeesDataSource)

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return employeesViewModelFactory
    }
}