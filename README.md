# IWantToBeMVP

## Preview

<div>
    <img src="https://github.com/BE-SOPT-28th-Android-SongShinGwanHan/IWantToBeMVP/blob/hunki/first_assignment/1%EC%B0%A8%EA%B3%BC%EC%A0%9C.gif" width="300" height="650"/>
</div>


## 내용

### 1주차 STEP1

```kotlin
<SignInPresenter>
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
<SignInActivity>
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
        // startActivityForResult 이거 바뀐거 사용해야 해서 차차
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showToast(message: String) {
        shortToast(message)
    }
기존의 것들도 전부 MVP패턴을 사용해 구현을 하였고 Presenter에 있는 interface를 구현함으로써 뷰와 모델간의 중재자 역할을 수행할 수 있습니다.
```

<div>
    <img src="https://github.com/BE-SOPT-28th-Android-SongShinGwanHan/IWantToBeMVP/blob/hunki/first_assignment/%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0%20%EB%A1%9C%EA%B7%B8.PNG" width="300" height="300"
</div>


