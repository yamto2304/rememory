package com.sunasterisk.rememory.ui.main.home

import android.view.View
import com.sunasterisk.rememory.BaseFragment
import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.local.WorkLocalDataSource
import com.sunasterisk.rememory.data.source.local.dao.WorkDaoImpl
import com.sunasterisk.rememory.database.SQLiteHelper
import com.sunasterisk.rememory.toast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), WorkContract.View, WorkAdapter.ClickListener {
    override val layoutResource get() = R.layout.fragment_home
    override fun initComponent() {
        initData()
        initRecycleView()
    }

    private var workAdapter = WorkAdapter()
    private var presenter: WorkContract.Presenter? = null
    private fun initData() {
        context?.let {
            val db = SQLiteHelper.getInstance(it)
            val worksDao = WorkDaoImpl.getInstance(db)
            val localRepository = WorksRepository.getInstance(
                local = WorkLocalDataSource.getInstance(worksDao)
            )
            presenter = WorkPresenter(this, localRepository)
            presenter?.getAllWorks()
        }
    }

    private fun initRecycleView() = with(recycleViewWorksList) {
        adapter = workAdapter
    }

    override fun showWorks(works: List<Work>) {
        workAdapter.apply {
            updateData(works)
            setOnClickListener(this@HomeFragment)
        }
        if (works.isEmpty()) {
            linearNoTasksLayout.visibility = View.VISIBLE
            recycleViewWorksList.visibility = View.INVISIBLE
        } else {
            linearNoTasksLayout.visibility = View.INVISIBLE
            recycleViewWorksList.visibility = View.VISIBLE
        }
    }

    override fun showError(exception: Exception) {
        context?.toast(exception.message.toString())
    }

    override fun onItemClick(id: Int) {
        TODO("Not yet implemented")
    }
}
