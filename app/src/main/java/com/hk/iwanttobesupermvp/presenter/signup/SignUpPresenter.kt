package com.hk.iwanttobesupermvp.presenter.signup

import com.hk.iwanttobesupermvp.api.data.entity.signup.RequestSignUp
import com.hk.iwanttobesupermvp.api.data.entity.signup.ResponseSignUp
import com.hk.iwanttobesupermvp.api.local.database.SampleDbHelper
import com.hk.iwanttobesupermvp.contract.activity.signup.SignUpContract
import com.hk.iwanttobesupermvp.domain.asDatabaseUser
import com.hk.iwanttobesupermvp.domain.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private var signUpView: SignUpContract.SignUpView,
    private val signUpModel: SignUpContract.SignUpModel,
    private val appDatabase: SampleDbHelper,
    private val authRepository: AuthRepository
) : SignUpContract.SignUpPresenter {
    override fun onSignUpButtonClick() {
        signUpView.getSignUpUserInfo().let { signUpModel.setSignUpUserInfo(it) }
        if (signUpModel.isValidate()) {
            signUpView.getSignUpUserInfo().let {
                appDatabase.insertUser(it.asDatabaseUser())
            }
            authRepository.signUp(
                RequestSignUp(
                    signUpModel.getSignUpUserInfo().githubId,
                    signUpModel.getSignUpUserInfo().password,
                    "0", signUpModel.getSignUpUserInfo().name,
                    "010-1111-1111",
                    "1996-08-17"
                )
            ).enqueue(object : Callback<ResponseSignUp> {
                override fun onResponse(
                    call: Call<ResponseSignUp>,
                    response: Response<ResponseSignUp>
                ) {
                    if(response.isSuccessful)
                        signUpView.navigateToSignInPage()
                }
                override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                    signUpView.showToast("${t.message}")
                }
            })
        } else {
            signUpView.showToast("빈 칸이 있는지 확인해주세요.")
        }
    }
}