package com.hk.iwanttobesupermvp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MockDataEntity(
    val repositoryName: String,
    val repositoryDescription: String,
    val userLanguage: String
) : Parcelable