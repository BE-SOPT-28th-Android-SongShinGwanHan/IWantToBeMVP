package com.hk.iwanttobesupermvp.api.local.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.hk.iwanttobesupermvp.api.local.entity.DatabaseUser
import com.hk.iwanttobesupermvp.util.TestKeyStore

class TestDbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(
        context,
        TestKeyStore.provideLocalDatabaseName(),
        factory,
        TestKeyStore.provideLocalDatabaseVersion()
    ) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_USER_NAME + "," +
                COLUMN_USER_GITHUB_ID + "," +
                COLUMN_PASSWORD
                + " TEXT" + ")")
        db?.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Database CRUD
    fun insertUser(user: DatabaseUser) {
        val values = ContentValues()
        putUser(values, user)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
    }

    fun getAllUser(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getDatabaseSize(): Int {
        var count = 1
        val cursor = getAllUser()
        cursor?.moveToFirst()
        while (cursor?.moveToNext()!!)
            count++
        Log.d("hello", count.toString())
        return count
    }

    private fun putUser(values: ContentValues, user: DatabaseUser) {
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_GITHUB_ID, user.githubId)
        values.put(COLUMN_PASSWORD, user.password)
    }

    // 스키마
    // SQL 데이터베이스의 기본 원칙 중 하나
    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "_id"
        const val COLUMN_USER_NAME = "usersName"
        const val COLUMN_USER_GITHUB_ID = "githubId"
        const val COLUMN_PASSWORD = "password"
    }
}