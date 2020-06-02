package com.sunasterisk.rememory.ui.addwork

import android.annotation.SuppressLint
import android.widget.PopupMenu
import com.sunasterisk.rememory.BaseFragment
import com.sunasterisk.rememory.R
import kotlinx.android.synthetic.main.fragment_add_work.*

class AddWorkFragment : BaseFragment(), AddWorkContract.View {
    override val layoutResource get() = R.layout.fragment_add_work
    override fun initComponent() {
        showClassifyMenu()
        showEvaluateMenu()
    }

    @SuppressLint("SetTextI18n")
    private fun showClassifyMenu() {
        buttonClassify.setOnClickListener {
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
    private fun showEvaluateMenu() {
        var evaluateImportant: String? = ""
        var evaluateRush: String? = ""
        buttonEvaluate.setOnClickListener {
            val evaluateMenu = PopupMenu(activity, buttonEvaluate)
            evaluateMenu.apply {
                menuInflater.inflate(R.menu.evaluate_menu, evaluateMenu.menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.evaluateImportant -> evaluateImportant =
                            getString(R.string.evaluate_important)
                        R.id.evaluateNotImportant -> evaluateImportant =
                            getString(R.string.evaluate_not_important)
                        R.id.evaluateNotRush -> evaluateRush = getString(R.string.evaluate_not_rush)
                        R.id.evaluateRush -> evaluateRush = getString(R.string.evaluate_rush)
                    }
                    buttonEvaluate.text = evaluateImportant + '\n' + evaluateRush
                    true
                }
                show()
            }
        }
    }
}
