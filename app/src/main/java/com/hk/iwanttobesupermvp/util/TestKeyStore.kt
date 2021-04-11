package com.hk.iwanttobesupermvp.util

object TestKeyStore {
    private const val DATABASE_NAME = "test-Database"
    private const val DATABASE_VERSION = 2

    fun provideLocalDatabaseName() = DATABASE_NAME

    fun provideLocalDatabaseVersion() = DATABASE_VERSION
}