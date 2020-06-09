package com.sunasterisk.rememory.data.source.local.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.sunasterisk.rememory.data.model.Work
import com.sunasterisk.rememory.database.AppDatabase
import com.sunasterisk.rememory.database.SQLiteHelper


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
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

    override fun getWorks(workId: String): Work {
        val cursor =
            readableDatabase.query(
                AppDatabase.TABLE_WORKS,
                null,
                AppDatabase.COL_ID_WORK + " =? ",
                arrayOf(workId),
                null,
                null,
                null
            ).apply {
                moveToFirst()
            }
        return Work(cursor)
    }

    @SuppressLint("SimpleDateFormat")
    override fun getProgressInDay(day: String): Int {
        var workDone: Int = 0
        var totalWork: Int = 0
        val progress: Int
        val cursor =
            readableDatabase.query(AppDatabase.TABLE_WORKS, null, null, null, null, null, null)
                .apply {
                    moveToFirst()
                }
        while (!cursor.isAfterLast) {
            val stringDateData = Work(cursor).workDate
            if (stringDateData == day && (Work(cursor).workProgress == 1)) {
                workDone += 1
            }
            cursor.moveToNext()
            totalWork++

        }
        progress = workDone * 100 / totalWork
        cursor.close()

        return progress
    }

    override fun updateProgress(id: String): Boolean {
        val work = getWorks(id)

        var progress = work.workProgress
        progress = if (progress == 0) {
            1
        } else {
            0
        }
        val contentValues = ContentValues()
        contentValues.put(AppDatabase.COL_PROGRESS_WORK, progress)
        return writableDatabase.update(
            AppDatabase.TABLE_WORKS,
            contentValues,
            AppDatabase.COL_ID_WORK + " = ?",
            arrayOf(id)
        ) > 0
    }

    override fun addWork(work: Work): Boolean {
        return writableDatabase.insert(
            AppDatabase.TABLE_WORKS,
            null,
            work.getContentValues()
        ) > 0
    }

    override fun deleteWork(id: String): Boolean {
        return writableDatabase.delete(
            AppDatabase.TABLE_WORKS,
            AppDatabase.COL_ID_WORK + " = ?",
            arrayOf(id)
        ) > 0
    }

    override fun updateWork(work: Work): Boolean {
        val contentValues = ContentValues()
        contentValues.put(AppDatabase.COL_CLASSIFY_WORK, work.workClassify)
        contentValues.put(AppDatabase.COL_DETAILS_WORK, work.workDetails)
        contentValues.put(AppDatabase.COL_EVALUATE_IMPORTANT_WORK, work.workImportantEvaluate)
        contentValues.put(AppDatabase.COL_EVALUATE_RUSH_WORK, work.workRushEvaluate)

        return writableDatabase.update(
            AppDatabase.TABLE_WORKS,
            contentValues,
            AppDatabase.COL_ID_WORK + " = ?",
            arrayOf(work.workId)
        ) > 0
    }

    companion object {
        private var instance: WorkDaoImpl? = null
        fun getInstance(helper: SQLiteHelper) =
            instance ?: WorkDaoImpl(helper).also { instance = it }
    }
}

