package com.sunasterisk.rememory.ui.addwork

import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback
import java.lang.Exception

class AddWorkPresenter(
    private val view: AddWorkContract.View,
    private val repository: WorksRepository
) : AddWorkContract.Presenter {
    override fun addWork(work: Work) {
        repository.addWork(work, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                val notifyResponse =
                    if (data) {
                        R.string.notify_insert_success
                    } else {
                        R.string.notify_insert_failed
                    }
                view.showNotify(notifyResponse)
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }
        })
    }
}
