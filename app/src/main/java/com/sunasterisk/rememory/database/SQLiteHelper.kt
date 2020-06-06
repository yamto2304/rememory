package com.sunasterisk.rememory.database


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper private constructor(context: Context, dbName: String, version: Int) :
    SQLiteOpenHelper(context, dbName, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            execSQL(AppDatabase.CREATE_TABLE_WORKS)
            execSQL(AppDatabase.CREATE_TABLE_CLASSIFY)
            execSQL(AppDatabase.CREATE_TABLE_EVALUATE)
            execSQL(AppDatabase.CREATE_TABLE_HISTORY)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
            execSQL("DROP TABLE IF EXISTS" + AppDatabase.CREATE_TABLE_WORKS)
            execSQL("DROP TABLE IF EXISTS" + AppDatabase.CREATE_TABLE_CLASSIFY)
            execSQL("DROP TABLE IF EXISTS" + AppDatabase.CREATE_TABLE_EVALUATE)
            execSQL("DROP TABLE IF EXISTS" + AppDatabase.CREATE_TABLE_HISTORY)
        }
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "db_todo"
        private const val DATABASE_VERSION = 1
        private var instance: SQLiteHelper? = null

        fun getInstance(context: Context) =
            instance ?: SQLiteHelper(context, DATABASE_NAME, DATABASE_VERSION).also {
                instance = it
            }

    }
}
