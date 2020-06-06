package com.sunasterisk.rememory.data.source.local.dao

import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.database.AppDatabase
import com.sunasterisk.rememory.database.SQLiteHelper

class WorkDaoImpl private constructor(helper: SQLiteHelper) : WorkDao {
    private val writableDatabase = helper.writableDatabase
    private val readableDatabase = helper.readableDatabase

    override fun getAllWorks(): List<Work> {
        val cursor =
            readableDatabase.query(AppDatabase.TABLE_WORKS, null, null, null, null, null, null)
                .apply {
                    moveToFirst()
                }
        return mutableListOf<Work>().apply {
            while (!cursor.isAfterLast) {
                add(Work(cursor))
                cursor.moveToNext()
            }
            cursor.close()
        }
    }

    override fun getWorks(workId: String): List<Work> {
        val cursor =
            readableDatabase.query(Work.TABLE_NAME, null, null, null, null, null, null).apply {
                moveToFirst()
            }
        return mutableListOf<Work>().apply {
            while (!cursor.isAfterLast) {
                add(Work(cursor))
                cursor.moveToNext()
            }
            cursor.close()
        }
    }

    override fun addWork(work: Work): Boolean {
        return writableDatabase.insert(
            AppDatabase.TABLE_WORKS,
            null,
            work.getContentValues()
        ) > 0
    }


    override fun deleteWork(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateWork(work: Work) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: WorkDaoImpl? = null
        fun getInstance(helper: SQLiteHelper) =
            instance ?: WorkDaoImpl(helper).also { instance = it }
    }
}
