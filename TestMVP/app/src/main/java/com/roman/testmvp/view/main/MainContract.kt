package com.roman.testmvp.view.main

import com.roman.testmvp.model.Employee
import com.roman.testmvp.view.base.BaseContract

class MainContract  {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Employee>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadEmployees()
    }
}