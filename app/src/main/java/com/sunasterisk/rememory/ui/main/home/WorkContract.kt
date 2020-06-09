package com.sunasterisk.rememory.ui.main.home

import com.sunasterisk.rememory.data.model.Work
import java.lang.Exception

interface WorkContract {
    interface View {
        fun editWork(work: Work)
        fun refreshFragment()
        fun showDetails(works: Work)
        fun showWorks(works: List<Work>)
        fun showError(exception: Exception)
        fun showNotify(notifyId: Int)
    }

    interface Presenter {
        fun updateProgress(id: String)
        fun getAllWorks()
        fun getWorkDetails(id: String)
        fun deleteWorkById(id: String)
        fun editWorkById(id: String)
        fun updateWork(work: Work)
    }
}
