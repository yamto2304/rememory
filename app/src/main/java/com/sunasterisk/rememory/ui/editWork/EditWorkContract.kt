package com.sunasterisk.rememory.ui.editWork

import com.sunasterisk.rememory.data.model.Work

interface EditWorkContract {
    interface View {
        fun showNotify(notifyId: Int)
        fun showError(exception: Exception)
    }

    interface Presenter {
        fun updateWork(work: Work)
    }
}
