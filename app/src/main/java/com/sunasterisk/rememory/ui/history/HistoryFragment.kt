package com.sunasterisk.rememory.ui.history

import android.annotation.SuppressLint
import android.os.Handler
import com.sunasterisk.rememory.BaseFragment
import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.local.WorkLocalDataSource
import com.sunasterisk.rememory.data.source.local.dao.WorkDaoImpl
import com.sunasterisk.rememory.database.SQLiteHelper
import com.sunasterisk.rememory.toast
import kotlinx.android.synthetic.main.fragment_history.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : BaseFragment(), HistoryContract.View {
    @SuppressLint("SimpleDateFormat")
    val formatDate = SimpleDateFormat("dd:MM:yyyy")
    private val today = formatDate.format(Calendar.getInstance().time)

    override val layoutResource: Int get() = R.layout.fragment_history
    override fun initComponent() {
        initData()
    }

    private var presenter: HistoryContract.Presenter? = null
    private fun initData() {
        context?.let {
            val db = SQLiteHelper.getInstance(it)
            val worksDao = WorkDaoImpl.getInstance(db)
            val localRepository = WorksRepository.getInstance(
                local = WorkLocalDataSource.getInstance(worksDao)
            )
            presenter = HistoryPresenter(this, localRepository)
            presenter?.getProgressInDay(today)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showDayProgress(progress: Int) {
        var status = 0
        val handler = Handler()
        textProgress.text = progress.toString()
        progressBar.progress = progress

        Thread(Runnable {
            while (status < progress) {
                status += 1
                handler.post(Runnable {
                    progressBar.progress = status
                    textProgress.text = String.format("%d%%", status)
                })
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()
    }

    override fun showError(exception: Exception) {
        context?.toast(exception.message.toString())
    }
}
