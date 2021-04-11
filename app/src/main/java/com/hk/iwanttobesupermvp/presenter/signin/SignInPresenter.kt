package com.hk.iwanttobesupermvp.presenter.signin

import com.hk.iwanttobesupermvp.contract.signin.SignInContract

class SignInPresenter(
    private var signInView: SignInContract.SignInView?,
    private val signInModel: SignInContract.SignInModel
) : SignInContract.SignInPresenter {
    override fun onLoginButtonClick() {
        signInView?.getUserStatus()?.let { signInModel.setUserInfo(it) }
        if (signInModel.isValidate()) {
            signInView?.navigateToHome()
        } else {
            signInView?.showToast(message = "아이디 혹은 비밀번호를 입력하세요.")
        }
    }

    override fun onSignUpTextClick() {
        signInView?.navigateToSignUpPage()
    }

    override fun onDestroy() {
        signInView = null
    }

}