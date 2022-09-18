package com.batuhandemirbas.studentapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.batuhandemirbas.studentapp.model.Student

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

    fun writeData(number: Int, name: String, password: Int, context: Context?) {
        // Connect db
        val db = DatabaseHelper(context)

        // Gets the data repository in write mode
        val dbWrite = db.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(TableContract.TableEntry.COLUMN_NUMBER, number)
            put(TableContract.TableEntry.COLUMN_NAME, name)
            put(TableContract.TableEntry.COLUMN_PASSWORD, password)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = dbWrite?.insert(TableContract.TableEntry.TABLE_NAME, null, values)
    }

    fun readDataStudent(number: Int, context: Context?): Student {
        //db
        val db = DatabaseHelper(context)
        val dbRead = db.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            TableContract.TableEntry.COLUMN_NUMBER,
            TableContract.TableEntry.COLUMN_NAME,
            TableContract.TableEntry.COLUMN_PASSWORD
        )

        // Filter results WHERE "title" = 'My Title'
        val selection = "${TableContract.TableEntry.COLUMN_NUMBER} = ?"
        val selectionArgs = arrayOf(number.toString())

        val cursor = dbRead.query(
            TableContract.TableEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        // DATA
        val students = mutableListOf<Student>()
        with(cursor) {
            while (moveToNext()) {
                val userNumber =
                    getInt(getColumnIndexOrThrow(TableContract.TableEntry.COLUMN_NUMBER))
                val userName =
                    getString(getColumnIndexOrThrow(TableContract.TableEntry.COLUMN_NAME))
                val userPassword =
                    getInt(getColumnIndexOrThrow(TableContract.TableEntry.COLUMN_PASSWORD))

                students.add(Student(userNumber, userName, userPassword))
            }
        }
        cursor.close()
        return students[0]

    }

    fun updateData (number: Int, name: String, newPassword: Int, context: Context?) {
        val db = DatabaseHelper(context)
        val dbWrite = db.writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(TableContract.TableEntry.COLUMN_PASSWORD, newPassword)
        }

// Which row to update, based on the title
        val selection = "${TableContract.TableEntry.COLUMN_NUMBER} LIKE ?"
        val selectionArgs = arrayOf(number.toString())
        val count = dbWrite.update(
            TableContract.TableEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs)
    }
}