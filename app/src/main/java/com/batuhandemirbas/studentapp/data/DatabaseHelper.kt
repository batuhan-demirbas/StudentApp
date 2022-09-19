package com.batuhandemirbas.studentapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.batuhandemirbas.studentapp.model.Meal
import com.batuhandemirbas.studentapp.model.Student

object StudentContract {
    // Table contents are grouped together in an anonymous object.
    object StudentEntry : BaseColumns {
        const val TABLE_NAME = "students"
        const val COLUMN_NUMBER = "number"
        const val COLUMN_NAME = "name"
        const val COLUMN_PASSWORD = "password"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${StudentEntry.TABLE_NAME} (" +
                "${StudentEntry.COLUMN_NUMBER} INTEGER," +
                "${StudentEntry.COLUMN_NAME} TEXT," +
                "${StudentEntry.COLUMN_PASSWORD} INTEGER)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${StudentEntry.TABLE_NAME}"
}

object MealContract {
    // Table contents are grouped together in an anonymous object.
    object MealEntry : BaseColumns {
        const val TABLE_NAME = "meals"
        const val COLUMN_DATE = "date"
        const val COLUMN_MAIN = "main"
        const val COLUMN_SOUP = "soup"
        const val COLUMN_SNACK = "snack"
        const val COLUMN_SIDE = "side"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${MealEntry.TABLE_NAME} (" +
                "${MealEntry.COLUMN_DATE} TEXT," +
                "${MealEntry.COLUMN_MAIN} TEXT," +
                "${MealEntry.COLUMN_SOUP} TEXT," +
                "${MealEntry.COLUMN_SNACK} TEXT," +
                "${MealEntry.COLUMN_SIDE} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MealEntry.TABLE_NAME}"
}

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Student database create
        db.execSQL(StudentContract.SQL_CREATE_ENTRIES)
        // Meal database create
        db.execSQL(MealContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        // Student database delete entries
        db.execSQL(StudentContract.SQL_DELETE_ENTRIES)

        // Meal database delete entries
        db.execSQL(StudentContract.SQL_DELETE_ENTRIES)

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
            put(StudentContract.StudentEntry.COLUMN_NUMBER, number)
            put(StudentContract.StudentEntry.COLUMN_NAME, name)
            put(StudentContract.StudentEntry.COLUMN_PASSWORD, password)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = dbWrite?.insert(StudentContract.StudentEntry.TABLE_NAME, null, values)
    }

    fun writeMealData(context: Context?) {
        // Connect db
        val db = DatabaseHelper(context)

        // Gets the data repository in write mode
        val dbWrite = db.writableDatabase

        // Create a new map of values, where column names are the keys
        val meal1 = ContentValues().apply {
            put(MealContract.MealEntry.COLUMN_DATE, "19.09.2022")
            put(MealContract.MealEntry.COLUMN_MAIN, "Tas Kebabı")
            put(MealContract.MealEntry.COLUMN_SOUP, "Kırmızı Mercimek Çorbası")
            put(MealContract.MealEntry.COLUMN_SNACK, "İmambayıldı")
            put(MealContract.MealEntry.COLUMN_SIDE, "Yoğurt")
        }

        val meal2 = ContentValues().apply {
            put(MealContract.MealEntry.COLUMN_DATE, "20.09.2022")
            put(MealContract.MealEntry.COLUMN_MAIN, "Kıymalı Kuru Fasulye")
            put(MealContract.MealEntry.COLUMN_SOUP, "Tavuk Suyu Şehriye Çorba")
            put(MealContract.MealEntry.COLUMN_SNACK, "Tel Şehriye Bulgur")
            put(MealContract.MealEntry.COLUMN_SIDE, "Meyve")
        }

        // Insert the new row, returning the primary key value of the new row
        dbWrite?.insert(MealContract.MealEntry.TABLE_NAME, null, meal1)
        dbWrite?.insert(MealContract.MealEntry.TABLE_NAME, null, meal2)
    }

    fun readDataStudent(number: Int, context: Context?): Student {
        //db
        val db = DatabaseHelper(context)
        val dbRead = db.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            StudentContract.StudentEntry.COLUMN_NUMBER,
            StudentContract.StudentEntry.COLUMN_NAME,
            StudentContract.StudentEntry.COLUMN_PASSWORD
        )

        // Filter results WHERE "title" = 'My Title'
        val selection = "${StudentContract.StudentEntry.COLUMN_NUMBER} = ?"
        val selectionArgs = arrayOf(number.toString())

        val cursor = dbRead.query(
            StudentContract.StudentEntry.TABLE_NAME,   // The table to query
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
                    getInt(getColumnIndexOrThrow(StudentContract.StudentEntry.COLUMN_NUMBER))
                val userName =
                    getString(getColumnIndexOrThrow(StudentContract.StudentEntry.COLUMN_NAME))
                val userPassword =
                    getInt(getColumnIndexOrThrow(StudentContract.StudentEntry.COLUMN_PASSWORD))

                students.add(Student(userNumber, userName, userPassword))
            }
        }
        cursor.close()
        return students[0]

    }

    fun readDataMeals(context: Context?): MutableList<Meal> {

        //db
        val db = DatabaseHelper(context)
        val dbRead = db.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            MealContract.MealEntry.COLUMN_DATE,
            MealContract.MealEntry.COLUMN_MAIN,
            MealContract.MealEntry.COLUMN_SOUP,
            MealContract.MealEntry.COLUMN_SNACK,
            MealContract.MealEntry.COLUMN_SIDE
        )

        val cursor = dbRead.query(
            MealContract.MealEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        // DATA
        val Meals = mutableListOf<Meal>()
        with(cursor) {
            while (moveToNext()) {
                val mealDate = getString(getColumnIndexOrThrow(MealContract.MealEntry.COLUMN_DATE))
                val mealMain = getString(getColumnIndexOrThrow(MealContract.MealEntry.COLUMN_MAIN))
                val mealSoup = getString(getColumnIndexOrThrow(MealContract.MealEntry.COLUMN_SOUP))
                val mealSnack =
                    getString(getColumnIndexOrThrow(MealContract.MealEntry.COLUMN_SNACK))
                val mealSide = getString(getColumnIndexOrThrow(MealContract.MealEntry.COLUMN_SIDE))

                Meals.add(Meal(mealDate, mealMain, mealSoup, mealSnack, mealSide))
            }
        }
        cursor.close()
        return Meals

    }

    fun updateData(number: Int, name: String, newPassword: Int, context: Context?) {
        val db = DatabaseHelper(context)
        val dbWrite = db.writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(StudentContract.StudentEntry.COLUMN_PASSWORD, newPassword)
        }

// Which row to update, based on the title
        val selection = "${StudentContract.StudentEntry.COLUMN_NUMBER} LIKE ?"
        val selectionArgs = arrayOf(number.toString())
        val count = dbWrite.update(
            StudentContract.StudentEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
    }
}

