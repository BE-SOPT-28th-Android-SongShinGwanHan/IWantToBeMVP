package com.hk.iwanttobesupermvp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hk.iwanttobesupermvp.contract.signin.SignInContract
import com.hk.iwanttobesupermvp.databinding.ActivitySignInBinding
import com.hk.iwanttobesupermvp.domain.User
import com.hk.iwanttobesupermvp.model.SignInModel
import com.hk.iwanttobesupermvp.presenter.signin.SignInPresenter
import com.hk.iwanttobesupermvp.util.shortToast
import com.hk.iwanttobesupermvp.view.activity.contract.SignUpActivityContract
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity(), SignInContract.SignInView {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var signInPresenter: SignInPresenter

    // 다른 액티비티를 실행할때 특정 값을 가지고 시작을 해야될 경우엔 ,launcher 객체의 제네릭에 담아서
    // 특정 값을 가지지 않아도 될 경우에는 타입추론으로 해결가능
    private val signUpLauncher =
        registerForActivityResult(SignUpActivityContract()) { result: User? ->
            // 다른 액티비티로 다녀온 다음에 하고자 하는 일을 람다 코드블록으로 처리한다.
            binding.apply {
                signInIdEditText.setText(result?.githubId)
                signInPasswordEditText.setText(result?.password)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInPresenter = SignInPresenter(this, SignInModel())

        binding.signInLoginButton.setOnClickListener {
            signInPresenter.onLoginButtonClick()
        }
        binding.signInMoveSignUpText.setOnClickListener {
            signInPresenter.onSignUpTextClick()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        signInPresenter.onDestroy()
    }

    override fun getUserStatus(): User = User(
        binding.signInIdEditText.text.toString(),
        binding.signInPasswordEditText.text.toString()
    )

    override fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("id", binding.signInIdEditText.text.toString())
        startActivity(intent)
        finish()
    }

    override fun navigateToSignUpPage() {
        val intent = Intent(this, SignUpActivity::class.java)
        signUpLauncher.launch(intent)
    }

    override fun showToast(message: String) {
        shortToast(message)
    }
}