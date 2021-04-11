package com.hk.iwanttobesupermvp.contract.signup

import com.hk.iwanttobesupermvp.domain.SignUpUser

interface SignUpContract {
    interface SignUpView {
        fun getSignUpUserInfo(): SignUpUser

        fun navigateToSignInPage()

        fun showToast(message: String)
    }

    interface SignUpModel {
        fun isValidate(): Boolean

        fun setSignUpUserInfo(signUpUser: SignUpUser)
    }

    interface SignUpPresenter {
        fun onSignUpButtonClick()

        fun onDestroy()
    }
}