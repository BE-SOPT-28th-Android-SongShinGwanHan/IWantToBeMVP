package com.hk.iwanttobesupermvp.presenter.signup

import com.hk.iwanttobesupermvp.api.local.database.TestDbHelper
import com.hk.iwanttobesupermvp.contract.signup.SignUpContract
import com.hk.iwanttobesupermvp.domain.asDatabaseUser
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private var signUpView: SignUpContract.SignUpView,
    private val signUpModel: SignUpContract.SignUpModel,
    private val appDatabase: TestDbHelper
) : SignUpContract.SignUpPresenter {
    override fun onSignUpButtonClick() {
        signUpView.getSignUpUserInfo().let { signUpModel.setSignUpUserInfo(it) }
        if (signUpModel.isValidate()) {
            signUpView.getSignUpUserInfo().let {
                appDatabase.insertUser(it.asDatabaseUser())
            }
            signUpView.navigateToSignInPage()
        } else {
            signUpView.showToast("빈 칸이 있는지 확인해주세요.")
        }
    }
}