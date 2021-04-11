package com.hk.iwanttobesupermvp.model

import com.hk.iwanttobesupermvp.contract.signup.SignUpContract
import com.hk.iwanttobesupermvp.domain.SignUpUser

class SignUpModel : SignUpContract.SignUpModel {

    var signUpUser = SignUpUser("", "", "")

    override fun isValidate(): Boolean =
        signUpUser.name.isNotBlank() && signUpUser.githubId.isNotBlank() && signUpUser.password.isNotBlank()

    override fun setSignUpUserInfo(signUpUser: SignUpUser) {
        this.signUpUser = signUpUser
    }

}