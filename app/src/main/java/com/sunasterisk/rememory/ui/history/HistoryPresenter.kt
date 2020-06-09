package com.sunasterisk.rememory.ui.history

import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback
import java.lang.Exception

class HistoryPresenter(
    private val view: HistoryContract.View,
    private val workRepo: WorksRepository
) : HistoryContract.Presenter {

    override fun getProgressInDay(day: String) {
        workRepo.getProgressInDay(day, object : OnDataLoadedCallback<Int> {
            override fun onSuccess(data: Int) {
                view.showDayProgress(data)
            }

            override fun onFailure(exception: Exception) {
                view.showError(exception)
            }
        })
    }
}
