package com.sunasterisk.rememory.data.source.local.dao

import com.sunasterisk.rememory.data.model.Work

interface WorkDao {
    fun getAllWorks(): List<Work>

    fun getWorks(workId: String): List<Work>

    fun addWork(work: Work): Boolean

    fun deleteWork(id: Int): Boolean

    fun updateWork(work: Work)
}
