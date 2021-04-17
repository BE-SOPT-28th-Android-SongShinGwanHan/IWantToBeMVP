package com.hk.iwanttobesupermvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hk.iwanttobesupermvp.contract.activity.signup.SignUpContract
import com.hk.iwanttobesupermvp.databinding.ActivitySignUpBinding
import com.hk.iwanttobesupermvp.domain.SignUpUser
import com.hk.iwanttobesupermvp.domain.User
import com.hk.iwanttobesupermvp.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), SignUpContract.SignUpView {

    private lateinit var binding: ActivitySignUpBinding

    @Inject
    lateinit var signUpPresenter: SignUpContract.SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            signUpPresenter.onSignUpButtonClick()
        }
    }

    override fun getSignUpUserInfo(): SignUpUser = SignUpUser(
        binding.signUpNameEditText.text.toString(),
        binding.signUpIdEditText.text.toString(),
        binding.signUpPasswordEditText.text.toString()
    )

    override fun navigateToSignInPage() {
        setResult(
            RESULT_OK,
            Intent().apply {
                putExtra(
                    "SignedUser",
                    User(
                        binding.signUpIdEditText.text.toString(),
                        binding.signUpPasswordEditText.text.toString()
                    )
                )
            })
        finish()
    }

    override fun showToast(message: String) {
        shortToast(message)
    }
}