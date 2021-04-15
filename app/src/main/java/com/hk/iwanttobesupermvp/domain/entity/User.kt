package com.hk.iwanttobesupermvp.domain

import android.os.Parcelable
import com.hk.iwanttobesupermvp.api.local.entity.DatabaseUser
import kotlinx.parcelize.Parcelize

/*data class User(
    val githubId: String,
    val password: String
) : Parcelable {
    // 보조 생성자
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    // Parcel이라는 데이터 클래스로 직렬화하는 메서드
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(githubId)
        dest.writeString(password)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        // Parcel로 부터 데이터를 복원해 다시 User객체로 변환을 해주는 메소드
        override fun createFromParcel(source: Parcel): User {
            return User(source)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}*/

@Parcelize
data class User(
    val githubId: String,
    val password: String
) : Parcelable

data class SignUpUser(
    val name: String,
    val githubId: String,
    val password: String
)

fun SignUpUser.asDatabaseUser(): DatabaseUser {
    return DatabaseUser(
        name = name,
        githubId = githubId,
        password = password
    )
}
