package com.hk.iwanttobesupermvp.util

object SampleKeyStore {
    private const val DATABASE_NAME = "test-Database"
    private const val DATABASE_VERSION = 2
    private const val MOCK_BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"
    private const val ASSIGNMENT_BASE_URL = "http://cherishserver.com/"

    fun provideLocalDatabaseName() = DATABASE_NAME

    fun provideLocalDatabaseVersion() = DATABASE_VERSION

    fun provideMockBaseUrl() = MOCK_BASE_URL

    fun provideAssignmentBaseUrl() = ASSIGNMENT_BASE_URL
}