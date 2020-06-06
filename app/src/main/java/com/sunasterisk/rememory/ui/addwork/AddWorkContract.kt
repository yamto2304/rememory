package com.sunasterisk.rememory.ui.addwork

import com.sunasterisk.rememory.data.model.Work

interface AddWorkContract{
    interface View{
        fun showNotify(notifyId: Int)
        fun showError(exception: Exception)
    }

    interface Presenter{
        fun addWork(work: Work)
    }
}
