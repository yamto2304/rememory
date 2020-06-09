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

    override fun getAllWorks(callback: OnDataLoadedCallback<List<Work>>) {
        LocalAsyncTask<EmptyInput, List<Work>>(callback) {
            workDao.getAllWorks()
        }.execute(EmptyInput)
    }

    override fun getWorks(workId: String, callback: OnDataLoadedCallback<Work>) {
        LocalAsyncTask<EmptyInput, Work>(callback) {
            workDao.getWorks(workId)
        }.execute(EmptyInput)
    }

    override fun getProgressInDay(day: String, callback: OnDataLoadedCallback<Int>) {
        LocalAsyncTask<String, Int>(callback) {
            workDao.getProgressInDay(day)
        }.execute(day)
    }

    override fun updateProgress(id: String, callback: OnDataLoadedCallback<Boolean>) {
        LocalAsyncTask<String, Boolean>(callback) {
            workDao.updateProgress(id)
        }.execute(id)
    }

    override fun addWork(work: Work, callback: OnDataLoadedCallback<Boolean>) {
        LocalAsyncTask<Work, Boolean>(callback) {
            workDao.addWork(work)
        }.execute(work)
    }

    override fun deleteWork(workId: String, callback: OnDataLoadedCallback<Boolean>) {
        LocalAsyncTask<String, Boolean>(callback) {
            workDao.deleteWork(workId)
        }.execute(workId)
    }

    override fun updateWork(work: Work, callback: OnDataLoadedCallback<Boolean>) {
        LocalAsyncTask<Work, Boolean>(callback) {
            workDao.updateWork(work)
        }.execute(work)
    }

    companion object {
        private var instance: WorkLocalDataSource? = null
        fun getInstance(workDao: WorkDao): WorkLocalDataSource =
            instance ?: WorkLocalDataSource(workDao).also { instance = it }
    }
}
