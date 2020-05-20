package com.sunasterisk.rememory.data.source

import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback

class WorksRepository private constructor(
    private val local: WorkDataSource.Local
) : WorkDataSource.Local {

    override fun getWorks(workId: String, callback: OnDataLoadedCallback<List<Work>>) {
        local.getWorks(workId, callback)
    }

    override fun addWork(work: Work, callback: OnDataLoadedCallback<Boolean>) {
        local.addWork(work, callback)
    }

    override fun deleteWork(workId: String, callback: OnDataLoadedCallback<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun updateWork(work: Work, callback: OnDataLoadedCallback<Work>) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: WorksRepository? = null
        fun getInstance(local: WorkDataSource.Local) =
            instance ?: WorksRepository(local).also { instance = it }
    }
}
