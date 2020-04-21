package com.roman.testmvvm.model

import com.roman.testmvvm.data.OperationCallback

interface EmployeesDataSource {

    fun retrieveEmployees(callback: OperationCallback<Employee>)
    fun cancel()
}
