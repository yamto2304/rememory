package com.sunasterisk.rememory.ui.main.home

import android.view.View
import com.sunasterisk.rememory.BaseFragment
import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.toast
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Exception


class HomeFragment : BaseFragment(), WorkContract.View {
    override val layoutResource get() = R.layout.fragment_home
    override fun initComponent() {

    }

    override fun showWorks(works: List<Work>) {
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
}
