package com.hk.iwanttobesupermvp.contract.activity.signin

import com.hk.iwanttobesupermvp.domain.User

// MVP: View와 Model을 완전히 분리해서 사용하기 위함 , 핵심은 사용자 인터페이스(View)와 비즈니스로직(Model)을 분리하고 서로간의 상호작용을 다른객체(Presenter)에 위임해 영향을 최소
// Model: 내부적으로 쓰이는 데이터를 저장, 처리
// Presenter: Model의 로직으로 부터 나온 결과를 받아 View에 보내 UI변경
// View: Model에서 처리된 데이터를 Presenter를 통해 받아 사용자에게 보여준다. , Presenter에게 의존적 , View에서 발생하는 이벤트는 직접 핸들링 가능 그러나 Presenter에게 위임
// 위임방법은 액티비티가 뷰 인터페이스를 구현해 Presenter에서 코드를 만들 인터페이스를 갖도록하면 된다.

interface SignInContract {
    interface SignInView {
        fun getUserStatus(): User

        fun navigateToHome()

        fun navigateToSignUpPage()

        fun showToast(message: String)
    }

    interface SignInModel {
        fun isValidate(): Boolean

        fun setUserInfo(user: User)
    }

    interface SignInPresenter {
        fun onLoginButtonClick()

        fun onSignUpTextClick()
    }
}