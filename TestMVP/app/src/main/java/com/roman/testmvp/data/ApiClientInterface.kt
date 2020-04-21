package com.roman.testmvp.data

import com.roman.testmvp.model.Employee
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClientInterface {

    @GET("/v2/5e972b443000008c00b6dc60")
    fun getEmployeeList(): Observable<List<Employee>>

    companion object Factory {
        fun create(): ApiClientInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.mocky.io/")
                .build()

            return retrofit.create(ApiClientInterface::class.java)
        }
    }
}