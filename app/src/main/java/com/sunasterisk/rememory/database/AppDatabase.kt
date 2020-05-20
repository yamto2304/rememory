package com.sunasterisk.rememory.database

object AppDatabase {

    private const val TABLE_CLASSIFY = "Classify"
    private const val COL_ID_CLASSIFY = "Id"
    private const val COL_WORK_CLASSIFY = "workClassify"
    private const val COL_FAMILY_CLASSIFY = "familyClassify"
    private const val COL_RELAX_CLASSIFY = "relaxClassify"
    private const val COL_MYSELF_CLASSIFY = "myselfClassify"

    const val CREATE_TABLE_CLASSIFY = "CREATE TABLE $TABLE_CLASSIFY (" +
            "$COL_ID_CLASSIFY IDENTITY(1,1) PRIMARY KEY, " +
            "$COL_WORK_CLASSIFY TEXT, " +
            "$COL_FAMILY_CLASSIFY TEXT, " +
            "$COL_RELAX_CLASSIFY TEXT, " +
            "$COL_MYSELF_CLASSIFY TEXT)"

    private const val TABLE_EVALUATE = "Evaluate"
    private const val COL_ID_EVALUATE = "evaluateId"
    private const val COL_RUSH_EVALUATE = "rush"
    private const val COL_IMPORTANT_EVALUATE = "important"

    const val CREATE_TABLE_EVALUATE = "CREATE TABLE $TABLE_EVALUATE (" +
            "$COL_ID_EVALUATE IDENTITY(1,1) PRIMARY KEY, " +
            "$COL_RUSH_EVALUATE TEXT, " +
            "$COL_IMPORTANT_EVALUATE TEXT)"

    private const val TABLE_WORKS = "Works"
    private const val COL_ID_WORK = "workId"
    private const val COL_DETAILS_WORK = "workDetails"
    private const val COL_DATE_WORK = "workDate"
    private const val COL_PROGRESS_WORK = "workProgress"
    private const val COL_EVALUATE_WORK = "workEvaluate"
    private const val COL_CLASSIFY_WORK = "workClassify"

    const val CREATE_TABLE_WORKS = "CREATE TABLE $TABLE_WORKS (" +
            "$COL_ID_WORK IDENTITY(1,1) PRIMARY KEY, " +
            "$COL_DETAILS_WORK TEXT, " +
            "$COL_DATE_WORK TEXT, " +
            "$COL_PROGRESS_WORK TEXT, " +
            "$COL_EVALUATE_WORK TEXT, " +
            "$COL_CLASSIFY_WORK TEXT)"

    private const val TABLE_HISTORY = "History"
    private const val COL_DATE_HISTORY = "date"
    private const val COL_PROGRESS_HISTORY = "progress"

    const val CREATE_TABLE_HISTORY = "CREATE TABLE $TABLE_HISTORY (" +
            "$COL_DATE_HISTORY IDENTITY(1,1) PRIMARY KEY, " +
            "$COL_PROGRESS_HISTORY TEXT)"
}