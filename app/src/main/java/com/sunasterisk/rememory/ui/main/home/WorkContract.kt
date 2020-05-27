package com.sunasterisk.rememory.ui.main.home

import com.sunasterisk.rememory.data.model.Work
import java.lang.Exception

interface WorkContract {
    interface View {
        fun showWorks(works: List<Work>)
        fun showError(exception: Exception)
    }

    interface Presenter {
        fun getAllWorks()
    }
}
