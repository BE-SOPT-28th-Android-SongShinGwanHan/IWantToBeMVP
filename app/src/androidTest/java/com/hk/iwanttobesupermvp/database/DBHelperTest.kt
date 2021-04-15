package com.hk.iwanttobesupermvp.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.hk.iwanttobesupermvp.api.local.database.SampleDbHelper
import com.hk.iwanttobesupermvp.api.local.entity.DatabaseUser
import org.junit.After
import org.junit.Before
import org.junit.Test

class DBHelperTest {
    private lateinit var db: SampleDbHelper

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = SampleDbHelper(context, null)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun add() {
        val testList = listOf(
            DatabaseUser("1", "1", "1"),
            DatabaseUser("2", "2", "2"),
            DatabaseUser("3", "3", "3")
        )

        testList.forEach {
            db.insertUser(it)
        }

        assert(db.getDatabaseSize() == 3)
    }
}