package com.batuhandemirbas.studentapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.batuhandemirbas.studentapp.LoginFragment

object TableContract {
    // Table contents are grouped together in an anonymous object.
    object TableEntry : BaseColumns {
        const val TABLE_NAME = "students"
        const val COLUMN_NUMBER = "number"
        const val COLUMN_NAME = "name"
        const val COLUMN_PASSWORD = "password"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${TableEntry.TABLE_NAME} (" +
                "${TableEntry.COLUMN_NUMBER} INTEGER," +
                "${TableEntry.COLUMN_NAME} TEXT," +
                "${TableEntry.COLUMN_PASSWORD} INTEGER)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TableEntry.TABLE_NAME}"
}

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TableContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TableContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "StudentApp.db"
    }
}