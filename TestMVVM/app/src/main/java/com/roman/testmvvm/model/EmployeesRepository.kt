package com.roman.testmvvm.model

import android.util.Log
import com.roman.testmvvm.data.ApiClient
import com.roman.testmvvm.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeesRepository:EmployeesDataSource {

    private var call: Call<List<Employee>>?=null

    override fun retrieveEmployees(callback: OperationCallback<Employee>) {
        call= ApiClient.build()?.employees()
        call?.enqueue(object : Callback<List<Employee>> {
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                response.body()?.let {
                    if(response.isSuccessful){
                        Log.v("TAG", "data ${it.toString()}")
                        callback.onSuccess(it)
                    }else{
                        callback.onError(it.toString())
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.cancel()
    }
}