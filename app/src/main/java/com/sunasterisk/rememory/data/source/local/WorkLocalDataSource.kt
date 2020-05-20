package com.sunasterisk.rememory.data.source.local

import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorkDataSource
import com.sunasterisk.rememory.data.source.base.EmptyInput
import com.sunasterisk.rememory.data.source.base.LocalAsyncTask
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback
import com.sunasterisk.rememory.data.source.local.dao.WorkDao

class WorkLocalDataSource private constructor(
    private val workDao: WorkDao
) : WorkDataSource.Local {

    override fun getWorks(workId: String, callback: OnDataLoadedCallback<List<Work>>) {
        LocalAsyncTask<EmptyInput, List<Work>>(callback) {
            workDao.getWorks(workId)
        }.execute(EmptyInput)
    }

    override fun addWork(work: Work, callback: OnDataLoadedCallback<Boolean>) {
        LocalAsyncTask<Work, Boolean>(callback) {
            workDao.addWork(work)
        }.execute(work)
    }

    override fun deleteWork(id: String, callback: OnDataLoadedCallback<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun updateWork(task: Work, callback: OnDataLoadedCallback<Work>) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: WorkLocalDataSource? = null
        fun getInstance(workDao: WorkDao): WorkLocalDataSource =
            instance ?: WorkLocalDataSource(workDao).also { instance = it }
    }
}