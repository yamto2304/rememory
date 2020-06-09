package com.sunasterisk.rememory.ui.history

import java.lang.Exception

interface HistoryContract {
    interface View {
        fun showDayProgress(progress: Int)
        fun showError(exception: Exception)
    }

    interface Presenter {
        fun getProgressInDay(day: String)
    }
}

