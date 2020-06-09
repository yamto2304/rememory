package com.sunasterisk.rememory.data.source

import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.base.OnDataLoadedCallback

interface WorkDataSource {

    interface Local {
        fun getAllWorks(callback: OnDataLoadedCallback<List<Work>>)

        fun getWorks(workId: String, callback: OnDataLoadedCallback<Work>)

        fun getProgressInDay(day: String, callback: OnDataLoadedCallback<Int>)

        fun updateProgress(id: String, callback: OnDataLoadedCallback<Boolean>)

        fun addWork(work: Work, callback: OnDataLoadedCallback<Boolean>)

        fun deleteWork(workId: String, callback: OnDataLoadedCallback<Boolean>)

        fun updateWork(work: Work, callback: OnDataLoadedCallback<Boolean>)
    }

    interface Remote
}
