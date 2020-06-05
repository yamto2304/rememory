package com.sunasterisk.rememory.data.model

import android.content.ContentValues
import android.database.Cursor

data class Work(
    var workId: String = "",
    var workDetails: String = "",
    var workDate: String = "",
    var workProgress: Int = 0,
    var workEvaluate: String = "",
    var workClassify: String = ""
) {

    constructor(cursor: Cursor) : this(
        workId = cursor.getString(cursor.getColumnIndex(ID)),
        workDetails = cursor.getString(cursor.getColumnIndex(WORK)),
        workDate = cursor.getString(cursor.getColumnIndex(DATE)),
        workProgress = cursor.getInt(cursor.getColumnIndex(PROGRESS)),
        workEvaluate = cursor.getString(cursor.getColumnIndex(EVALUATE)),
        workClassify = cursor.getString(cursor.getColumnIndex(CLASSIFY))
    )

    fun getContentValues() = ContentValues().apply {
        put(WORK, workDetails)
        put(DATE, workDate)
        put(PROGRESS, workProgress)
        put(EVALUATE, workEvaluate)
        put(CLASSIFY, workClassify)
    }

    companion object {
        const val TABLE_NAME = "Work"
        const val ID = "workId"
        const val WORK = "workDetails"
        const val DATE = "workDate"
        const val PROGRESS = "workProgress"
        const val EVALUATE = "workEvaluate"
        const val CLASSIFY = "workClassify"
    }
}
