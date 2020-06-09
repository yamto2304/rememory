package com.sunasterisk.rememory.data.source.local.dao

import com.sunasterisk.rememory.data.model.Work

interface WorkDao {
    fun getAllWorks(): List<Work>

    fun getWorks(workId: String): Work

    fun getProgressInDay(day: String): Int

    fun updateProgress(id: String): Boolean

    fun addWork(work: Work): Boolean

    fun deleteWork(id: String): Boolean

    fun updateWork(work: Work): Boolean
}
