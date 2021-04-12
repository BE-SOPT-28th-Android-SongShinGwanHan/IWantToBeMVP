package com.hk.iwanttobesupermvp.model

import com.hk.iwanttobesupermvp.contract.signin.SignInContract
import com.hk.iwanttobesupermvp.domain.User
import javax.inject.Inject

class SignInModel @Inject constructor() : SignInContract.SignInModel {
    var user: User = User("", "")

    override fun isValidate(): Boolean =
        user.githubId.isNotBlank() && user.password.isNotBlank()

    override fun setUserInfo(user: User) {
        this.user = user
    }

}