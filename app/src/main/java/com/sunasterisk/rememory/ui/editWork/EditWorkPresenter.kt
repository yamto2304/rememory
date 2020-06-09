package com.sunasterisk.rememory.ui.editWork

import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback

class EditWorkPresenter(
    private val view: EditWorkFragment,
    private val repository: WorksRepository
) : EditWorkContract.Presenter {
    override fun updateWork(work: Work) {
        repository.updateWork(work, object : OnDataLoadedCallback<Boolean> {
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
