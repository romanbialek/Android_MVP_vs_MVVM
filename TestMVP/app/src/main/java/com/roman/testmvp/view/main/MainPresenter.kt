package com.roman.testmvp.view.main

import com.roman.testmvp.data.ApiClientInterface
import com.roman.testmvp.model.Employee
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainPresenter: MainContract.Presenter {

    private val api: ApiClientInterface = ApiClientInterface.create()
    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    override fun loadEmployees() {

        var subscribe = api.getEmployeeList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Employee>? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!.take(10))
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })
        subscriptions.add(subscribe)
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }
}