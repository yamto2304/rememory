package com.sunasterisk.rememory.ui.main.home

import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback
import java.lang.Exception

class WorkPresenter(
    private val view: WorkContract.View,
    private val workRepo: WorksRepository
) : WorkContract.Presenter {
    override fun updateProgress(id: String) {
        workRepo.updateProgress(id, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                val notifyResponse =
                    if (data) {
                        R.string.notify_update_success
                    } else {
                        R.string.notify_update_failed
                    }
                view.showNotify(notifyResponse)
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }
        })
    }

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

    override fun getWorkDetails(id: String) {
        workRepo.getWorks(id, object : OnDataLoadedCallback<Work> {
            override fun onSuccess(data: Work) {
                view.showDetails(data)
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }

        })
    }

    override fun deleteWorkById(id: String) {
        workRepo.deleteWork(id, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.refreshFragment()
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }
        })
    }

    override fun editWorkById(id: String) {
        workRepo.getWorks(id, object : OnDataLoadedCallback<Work> {
            override fun onSuccess(data: Work) {
                view.editWork(data)
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }
        })
    }

    override fun updateWork(work: Work) {
        workRepo.updateWork(work, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                val notifyResponse =
                    if (data) {
                        R.string.notify_update_success
                    } else {
                        R.string.notify_update_failed
                    }
                view.showNotify(notifyResponse)
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }
        })
    }
}
