package com.sunasterisk.rememory.data.source

import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback

interface WorkDataSource {

    interface Local {
        fun getWorks(workId: String, callback: OnDataLoadedCallback<List<Work>>)

        fun addWork(work: Work, callback: OnDataLoadedCallback<Boolean>)

        fun deleteWork(workId: String, callback: OnDataLoadedCallback<Boolean>)

        fun updateWork(work: Work, callback: OnDataLoadedCallback<Work>)
    }

    interface Remote
}
