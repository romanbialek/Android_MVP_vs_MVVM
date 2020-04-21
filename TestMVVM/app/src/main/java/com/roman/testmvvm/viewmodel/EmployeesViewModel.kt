package com.roman.testmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roman.testmvvm.data.OperationCallback
import com.roman.testmvvm.model.Employee
import com.roman.testmvvm.model.EmployeesDataSource

class EmployeesViewModel (private val repository: EmployeesDataSource): ViewModel() {

    private var _employees = MutableLiveData<List<Employee>>().apply { value = emptyList() }
    val employees: LiveData<List<Employee>> = _employees

    private val _isViewLoading= MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError= MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList= MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList


    fun loadEmployees(){
        _isViewLoading.postValue(true)
        repository.retrieveEmployees(object: OperationCallback<Employee> {
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( error)
            }

            override fun onSuccess(data: List<Employee>) {
                _isViewLoading.postValue(false)

                if(data.isEmpty()){
                    _isEmptyList.postValue(true)
                }else{
                    _employees.value= data
                }
            }
        })
    }

}