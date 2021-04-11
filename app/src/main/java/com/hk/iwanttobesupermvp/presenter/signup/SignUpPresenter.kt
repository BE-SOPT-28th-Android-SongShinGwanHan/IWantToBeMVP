package com.hk.iwanttobesupermvp.presenter.signup

import android.content.Context
import com.hk.iwanttobesupermvp.contract.signup.SignUpContract
import com.hk.iwanttobesupermvp.sqlite.databasehelper.TestDbHelper
import com.hk.iwanttobesupermvp.domain.asDatabaseUser

class SignUpPresenter(
    private var signUpView: SignUpContract.SignUpView?,
    private val signUpModel: SignUpContract.SignUpModel
) : SignUpContract.SignUpPresenter {
    override fun onSignUpButtonClick() {
        signUpView?.getSignUpUserInfo()?.let { signUpModel.setSignUpUserInfo(it) }
        if (signUpModel.isValidate()) {
            val dbHandler = TestDbHelper((signUpView as Context), null)
            signUpView?.getSignUpUserInfo()?.let {
                dbHandler.insertUser(it.asDatabaseUser())
            }
            signUpView?.navigateToSignInPage()
        } else {
            signUpView?.showToast("빈 칸이 있는지 확인해주세요.")
        }
    }

    override fun onDestroy() {
        signUpView = null
    }

}