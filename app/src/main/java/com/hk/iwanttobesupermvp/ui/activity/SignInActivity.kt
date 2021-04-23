package com.hk.iwanttobesupermvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.hk.iwanttobesupermvp.R
import com.hk.iwanttobesupermvp.contract.activity.signin.SignInContract
import com.hk.iwanttobesupermvp.databinding.ActivitySignInBinding
import com.hk.iwanttobesupermvp.domain.User
import com.hk.iwanttobesupermvp.presenter.signin.SignInPresenter
import com.hk.iwanttobesupermvp.ui.activity.contract.SignUpActivityContract
import com.hk.iwanttobesupermvp.util.ActivityBindingAutoCleared
import com.hk.iwanttobesupermvp.util.getIntent
import com.hk.iwanttobesupermvp.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity(), SignInContract.SignInView {
    private var binding: ActivitySignInBinding by ActivityBindingAutoCleared()

    @Inject
    lateinit var signInPresenter: SignInPresenter

    private val signUpLauncher =
        registerForActivityResult(SignUpActivityContract()) { result: User? ->
            binding.apply {
                signInIdEditText.setText(result?.githubId)
                signInPasswordEditText.setText(result?.password)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInLoginButton.setOnClickListener {
            signInPresenter.onLoginButtonClick()
        }
        binding.signInMoveSignUpText.setOnClickListener {
            signInPresenter.onSignUpTextClick()
        }
    }

    override fun getUserStatus(): User = User(
        binding.signInIdEditText.text.toString(),
        binding.signInPasswordEditText.text.toString()
    )

    override fun navigateToHome() {
        getIntent<HomeActivity>(binding.signInIdEditText.text.toString())
        startActivity<HomeActivity>(binding.signInIdEditText.text.toString())
        finish()
    }

    override fun navigateToSignUpPage() {
        signUpLauncher.launch(getIntent<SignUpActivity>())
    }

    override fun showToast(message: String) {
        shortToast(message)
    }

    override fun shakeEditText() {
        val shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.edittext_shake_animation)
        binding.apply {
            signInLogoView.startAnimation(shakeAnimation)
            signInIdEditTextField.startAnimation(shakeAnimation)
            signInPasswordEditTextField.startAnimation(shakeAnimation)
            signInLoginButton.startAnimation(shakeAnimation)
        }
    }
}

inline fun <reified T : Any> SignInActivity.getIntent(
    wantToSetId: String
): Intent {
    val intent = Intent(this, T::class.java)
    intent.putExtra("id", wantToSetId)
    return intent
}

inline fun <reified T : Any> SignInActivity.startActivity(
    wantToSetId: String
) =
    startActivity(getIntent<T>(wantToSetId))