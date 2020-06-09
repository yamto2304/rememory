package com.sunasterisk.rememory.ui.main.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.View
import android.widget.*
import com.sunasterisk.rememory.BaseFragment
import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.local.WorkLocalDataSource
import com.sunasterisk.rememory.data.source.local.dao.WorkDaoImpl
import com.sunasterisk.rememory.database.SQLiteHelper
import com.sunasterisk.rememory.toast
import com.sunasterisk.rememory.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.work_item.*
import java.text.SimpleDateFormat
import java.util.*

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

    override fun showNotify(notifyId: Int) {
        context?.toast(getString(notifyId))
    }

    override fun onItemClick(id: Int) {
        presenter?.getWorkDetails(id.toString())
        complete_checkbox.setOnClickListener {
            presenter?.updateProgress(id.toString())
        }
        refreshFragment()
    }

    override fun refreshFragment() {
        presenter?.getAllWorks()
    }

    override fun showDetails(works: Work) {
        val dialog = Dialog(activity!!)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.detail_dialog)
        dialog.show()
        val btnClose: Button = dialog.findViewById(R.id.buttonClose)
        val dateCreate: TextView = dialog.findViewById(R.id.timeCreate)
        val classify: TextView = dialog.findViewById(R.id.classifyDetail)
        val detailWork: TextView = dialog.findViewById(R.id.detailsWork)
        val rushEvaluate: TextView = dialog.findViewById(R.id.rushDetails)
        val importantEvaluate: TextView = dialog.findViewById(R.id.importantDetails)
        val btnDelete: Button = dialog.findViewById(R.id.buttonDelete)
        val btnEdit: Button = dialog.findViewById(R.id.buttonEdit)

        rushEvaluate.text = works.workRushEvaluate
        importantEvaluate.text = works.workImportantEvaluate
        detailWork.text = works.workDetails
        dateCreate.text = works.workDate
        classify.text = works.workClassify
        btnClose.setOnClickListener { dialog.dismiss() }
        btnDelete.setOnClickListener {
            presenter?.deleteWorkById(works.workId)
            dialog.dismiss()
        }
        btnEdit.setOnClickListener {
            presenter?.editWorkById(works.workId)
            dialog.dismiss()
        }

    }

    @SuppressLint("SimpleDateFormat")
    override fun editWork(work: Work) {
        val formatDate = SimpleDateFormat("dd:MM:yyyy")
        val dialog2 = Dialog(activity!!)
        dialog2.setCancelable(false)
        dialog2.setContentView(R.layout.fragment_edit_work)

        val btnRush: Button = dialog2.findViewById(R.id.buttonRushEvaluate1)
        btnRush.text = work.workRushEvaluate
        btnRush.setOnClickListener {
            val evaluateRushMenu = PopupMenu(activity, btnRush)
            evaluateRushMenu.apply {
                menuInflater.inflate(R.menu.rush_evaluate_menu, evaluateRushMenu.menu)
                setOnMenuItemClickListener { item ->
                    btnRush.text = when (item.itemId) {
                        R.id.evaluateNotRush -> getString(R.string.evaluate_not_rush)
                        else -> getString(R.string.evaluate_rush)
                    }
                    true
                }
                show()
            }
        }

        val btnImportant: Button = dialog2.findViewById(R.id.buttonImportantEvaluate1)
        btnImportant.text = work.workImportantEvaluate
        btnImportant.setOnClickListener {
            val evaluateMenu = PopupMenu(activity, btnImportant)
            evaluateMenu.apply {
                menuInflater.inflate(R.menu.important_evaluate_menu, evaluateMenu.menu)
                setOnMenuItemClickListener { item ->
                    btnImportant.text = when (item.itemId) {
                        R.id.evaluateImportant -> getString(R.string.evaluate_important)
                        else -> getString(R.string.evaluate_not_important)
                    }
                    true
                }
                show()
            }
        }

        val btnClassify: Button = dialog2.findViewById(R.id.buttonClassify1)
        btnClassify.text = work.workClassify
        btnClassify.setOnClickListener {
            val classifyMenu = PopupMenu(activity, btnClassify)
            classifyMenu.apply {
                menuInflater.inflate(R.menu.classify_menu, classifyMenu.menu)
                setOnMenuItemClickListener { item ->
                    btnClassify.text = when (item.itemId) {
                        R.id.classifyWork -> getString(R.string.title_classify_work)
                        R.id.classifyRelax -> getString(R.string.title_classify_relax)
                        R.id.classifyFamily -> getString(R.string.title_classify_family)
                        else -> getString(R.string.title_classify_myself)
                    }
                    true
                }
                show()
            }
        }


        val editText: EditText = dialog2.findViewById(R.id.updateWorkEditText)
        editText.setText(work.workDetails, TextView.BufferType.EDITABLE)

        val btnUpdate: Button = dialog2.findViewById(R.id.buttonUpdateNewWork)
        btnUpdate.setOnClickListener {
            presenter?.updateWork(
                Work(
                    work.workId,
                    editText.text.toString(),
                    formatDate.format(Calendar.getInstance().time),
                    0,
                    btnRush.text.toString(),
                    btnImportant.text.toString(),
                    btnClassify.text.toString()
                )
            )
            startActivity(context?.let { MainActivity.getIntent(it) })
            presenter?.getAllWorks()
        }
        dialog2.show()
    }
}
