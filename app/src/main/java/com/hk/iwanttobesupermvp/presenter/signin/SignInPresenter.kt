package com.hk.iwanttobesupermvp.presenter.signin

import com.hk.iwanttobesupermvp.api.data.entity.signin.RequestSignIn
import com.hk.iwanttobesupermvp.api.data.entity.signin.ResponseSignIn
import com.hk.iwanttobesupermvp.contract.activity.signin.SignInContract
import com.hk.iwanttobesupermvp.domain.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SignInPresenter @Inject constructor(
    private var signInView: SignInContract.SignInView,
    private val signInModel: SignInContract.SignInModel,
    private val authRepository: AuthRepository
) : SignInContract.SignInPresenter {
    override fun onLoginButtonClick() {
        signInView.getUserStatus().let { signInModel.setUserInfo(it) }
        if (signInModel.isValidate()) {
            authRepository.signIn(RequestSignIn(signInModel.getUserInfo().githubId,signInModel.getUserInfo().password)).enqueue(object :
                Callback<ResponseSignIn> {
                override fun onResponse(
                    call: Call<ResponseSignIn>,
                    response: Response<ResponseSignIn>
                ) {
                    if(response.isSuccessful)
                        signInView.navigateToHome()
                }
                override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                    signInView.showToast(message = t.message.toString())
                }
            })
        } else {
            signInView.shakeEditText()
            signInView.showToast(message = "아이디 혹은 비밀번호를 입력하세요.")
        }
    }

    override fun onSignUpTextClick() {
        signInView.navigateToSignUpPage()
    }
}