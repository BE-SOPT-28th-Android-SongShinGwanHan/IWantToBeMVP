package com.hk.iwanttobesupermvp.api.local.sqlite.databasehelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
                COLUMN_NAME
                + " TEXT" + ")")
        db?.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(user: DatabaseUser) {
        val values = ContentValues()
        values.put(COLUMN_NAME, user.name)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        /*db.close()*/
    }

    fun getAllUser(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    // 스키마
    // SQL 데이터베이스의 기본 원칙 중 하나
    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "usersName"
    }
}