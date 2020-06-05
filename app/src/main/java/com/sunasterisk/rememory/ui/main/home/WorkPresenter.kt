package com.sunasterisk.rememory.ui.main.home

import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback
import java.lang.Exception

class WorkPresenter(
    private val view: WorkContract.View,
    private val workRepo: WorksRepository
) : WorkContract.Presenter{

    override fun getAllWorks() {
        workRepo.getAllWorks(object : OnDataLoadedCallback<List<Work>> {
            override fun onSuccess(data: List<Work>) {
                view.showWorks(data)
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }
        })
    }
}
