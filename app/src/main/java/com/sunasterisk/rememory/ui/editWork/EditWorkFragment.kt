package com.sunasterisk.rememory.ui.editWork

import android.annotation.SuppressLint
import android.widget.PopupMenu
import com.sunasterisk.rememory.BaseFragment
import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.data.source.WorksRepository
import com.sunasterisk.rememory.data.source.local.WorkLocalDataSource
import com.sunasterisk.rememory.data.source.local.dao.WorkDaoImpl
import com.sunasterisk.rememory.database.SQLiteHelper
import com.sunasterisk.rememory.toast
import com.sunasterisk.rememory.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_add_work.addWorkEditText
import kotlinx.android.synthetic.main.fragment_add_work.buttonBack
import kotlinx.android.synthetic.main.fragment_add_work.buttonClassify
import kotlinx.android.synthetic.main.fragment_add_work.buttonImportantEvaluate
import kotlinx.android.synthetic.main.fragment_add_work.buttonRushEvaluate
import kotlinx.android.synthetic.main.fragment_edit_work.*
import java.text.SimpleDateFormat
import java.util.*

class EditWorkFragment : BaseFragment(), EditWorkContract.View {

    override val layoutResource get() = R.layout.fragment_edit_work
    override fun initComponent() {
        context?.toast("UpdateWork")
        initDatabase()
        showClassifyMenu()
        showRushEvaluateMenu()
        showImportantEvaluateMenu()
        updateWork()
        buttonBack.setOnClickListener {
            startActivity(context?.let { MainActivity.getIntent(it) })
        }
    }

    private var presenter: EditWorkContract.Presenter? = null
    private fun initDatabase() {
        context?.let {
            val db = SQLiteHelper.getInstance(it)
            val worksDao = WorkDaoImpl.getInstance(db)
            val localRepository = WorksRepository.getInstance(
                local = WorkLocalDataSource.getInstance(worksDao)
            )
            presenter = EditWorkPresenter(this, localRepository)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateWork() {
        val formatDate = SimpleDateFormat("dd:MM:yyyy")

        buttonUpdateNewWork.setOnClickListener {
            if (addWorkEditText.text.toString().trim().isEmpty()) {
                showMess("Work Details can't be empty !")
            } else if (buttonClassify.text.toString().trim()
                    .isEmpty() || buttonClassify.text == "Classify"
            ) {
                showMess("Please Classify the Work !")
            } else if (buttonRushEvaluate.text.toString().trim()
                    .isEmpty() || buttonRushEvaluate.text == "Is Rush?"
            ) {
                showMess("Please Evaluate the Work !")
            } else if (buttonImportantEvaluate.text.toString().trim()
                    .isEmpty() || buttonImportantEvaluate.text == "Is Important?"
            ) {
                showMess("Please Evaluate the Work !")
            } else {
                presenter?.updateWork(
                    Work(
                        "",
                        addWorkEditText.text.toString(),
                        formatDate.format(Calendar.getInstance().time),
                        0,
                        buttonRushEvaluate.text.toString(),
                        buttonImportantEvaluate.text.toString(),
                        buttonClassify.text.toString()
                    )
                )
                startActivity(context?.let { MainActivity.getIntent(it) })
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showClassifyMenu() {
        buttonClassify1.setOnClickListener {
            val classifyMenu = PopupMenu(activity, buttonClassify)
            classifyMenu.apply {
                menuInflater.inflate(R.menu.classify_menu, classifyMenu.menu)
                setOnMenuItemClickListener { item ->
                    buttonClassify.text = when (item.itemId) {
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
    }

    @SuppressLint("SetTextI18n")
    private fun showRushEvaluateMenu() {
        buttonRushEvaluate1.setOnClickListener {
            val evaluateRushMenu = PopupMenu(activity, buttonRushEvaluate)
            evaluateRushMenu.apply {
                menuInflater.inflate(R.menu.rush_evaluate_menu, evaluateRushMenu.menu)
                setOnMenuItemClickListener { item ->
                    buttonRushEvaluate.text = when (item.itemId) {
                        R.id.evaluateNotRush -> getString(R.string.evaluate_not_rush)
                        else -> getString(R.string.evaluate_rush)
                    }
                    true
                }
                show()
            }
        }
    }

    private fun showImportantEvaluateMenu() {
        buttonImportantEvaluate1.setOnClickListener {
            val evaluateMenu = PopupMenu(activity, buttonImportantEvaluate)
            evaluateMenu.apply {
                menuInflater.inflate(R.menu.important_evaluate_menu, evaluateMenu.menu)
                setOnMenuItemClickListener { item ->
                    buttonImportantEvaluate.text = when (item.itemId) {
                        R.id.evaluateImportant -> getString(R.string.evaluate_important)
                        else -> getString(R.string.evaluate_not_important)
                    }
                    true
                }
                show()
            }
        }
    }

    override fun showNotify(notifyId: Int) {
        context?.toast(getString(notifyId))
    }

    override fun showError(exception: Exception) {
        context?.toast(exception.message.toString())
    }

    private fun showMess(mess: String) {
        context?.toast(mess)
    }
}
