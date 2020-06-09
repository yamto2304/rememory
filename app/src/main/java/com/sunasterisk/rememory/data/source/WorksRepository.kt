package com.sunasterisk.rememory.data.source

import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback

class WorksRepository private constructor(
    private val local: WorkDataSource.Local
) : WorkDataSource.Local {

    override fun getAllWorks(callback: OnDataLoadedCallback<List<Work>>) {
        local.getAllWorks(callback)
    }

    override fun getWorks(workId: String, callback: OnDataLoadedCallback<Work>) {
        local.getWorks(workId, callback)
    }

    override fun getProgressInDay(day: String, callback: OnDataLoadedCallback<Int>) {
        local.getProgressInDay(day, callback)
    }

    override fun updateProgress(id: String, callback: OnDataLoadedCallback<Boolean>) {
        local.updateProgress(id, callback)
    }

    override fun addWork(work: Work, callback: OnDataLoadedCallback<Boolean>) {
        local.addWork(work, callback)
    }

    override fun deleteWork(workId: String, callback: OnDataLoadedCallback<Boolean>) {
        local.deleteWork(workId, callback)
    }

    override fun updateWork(work: Work, callback: OnDataLoadedCallback<Boolean>) {
        local.updateWork(work, callback)
    }

    companion object {
        private var instance: WorksRepository? = null
        fun getInstance(local: WorkDataSource.Local) =
            instance ?: WorksRepository(local).also { instance = it }
    }
}
