package com.hk.iwanttobesupermvp.api.data.mock

import com.google.gson.annotations.SerializedName
import com.hk.iwanttobesupermvp.domain.entity.MockDataEntity

data class MockDataDTO(
    @SerializedName("name")
    val repositoryName: String,
    @SerializedName("email")
    val repositoryDescription: String,
    @SerializedName("avatar")
    val userLanguage: String
)

fun List<MockDataDTO>.asMockEntityData(): MutableList<MockDataEntity> {
    return map {
        MockDataEntity(
            repositoryName = it.repositoryName,
            repositoryDescription = it.repositoryDescription,
            userLanguage = it.userLanguage
        )
    } as MutableList<MockDataEntity>
}
