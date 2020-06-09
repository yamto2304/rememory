package com.sunasterisk.rememory.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.data.model.Work
import kotlinx.android.synthetic.main.work_item.view.*

class WorkAdapter : RecyclerView.Adapter<WorkAdapter.WorkViewHolder>() {

    private val works = mutableListOf<Work>()
    private var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder =
        WorkViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.work_item, parent, false)
        )
        { position ->
            clickListener?.onItemClick(works[position].workId.toInt())
        }

    override fun getItemCount(): Int = works.size

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        holder.bindData(works[position])
    }

    fun updateData(newArrWorks: List<Work>) {
        works.clear()
        works.addAll(newArrWorks)
        notifyDataSetChanged()
    }

    fun setOnClickListener(clickListener: ClickListener?) {
        this.clickListener = clickListener
    }

    class WorkViewHolder(itemView: View, private val onClickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClickListener(adapterPosition)
            }
        }

        fun bindData(works: Work) {
            val progressWork: Boolean = (works.workProgress == 1)
            itemView.title_text.text = works.workDetails
            itemView.complete_checkbox.isChecked = progressWork
        }
    }

    interface ClickListener {
        fun onItemClick(id: Int)
    }
}
