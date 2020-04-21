package com.roman.testmvvm.data

import com.roman.testmvvm.model.Employee

interface OperationCallback<T> {
    fun onSuccess(data: List<Employee>)
    fun onError(error:String?)
}