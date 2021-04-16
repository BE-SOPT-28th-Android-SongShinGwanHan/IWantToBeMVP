package com.hk.iwanttobesupermvp.di.presentermodule

import com.hk.iwanttobesupermvp.contract.signin.SignInContract
import com.hk.iwanttobesupermvp.model.SignInModel
import com.hk.iwanttobesupermvp.presenter.signin.SignInPresenter
import com.hk.iwanttobesupermvp.ui.activity.SignInActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

// MVP 패턴에서 주입을 받아야 할 것은 구현체인 녀석을 Provide받는 것이 아니다.
// 정확하게는 contract로 연결된 view,model,presenter를 주면됩니다.
// 왜냐면 이미 presenter에서 이미 interface를 받아 구현을 해놓았기 때문입니다.
// 물론 위의 구현체를 의존성으로 주입시켜주어도 되겠지만, 그렇게 된다면 이 module단에서 interface에 넣어놓은 필수 구현 메소드를 구현해야하는 아이러니한 상황이 발생할 것입니다.
// 그래서 제가 생각한 방법은 interface를 Presenter에게 주입시켜주는 방법을 선택했고
// 그렇기에 binds를 사용하였습니다.
// 실구현체를 넘겨줄때에는 @Provides , interface와 같이 아직 구현이 안되어있는 녀석을 주입시켜줄떈 @Binds를 해줍니다.

@Module
@InstallIn(ActivityComponent::class)
abstract class SignInModule {
    @Binds
    abstract fun bindActivity(activity: SignInActivity): SignInContract.SignInView

    @Binds
    abstract fun bindModel(model: SignInModel): SignInContract.SignInModel

    @Binds
    abstract fun bindPresenter(signInPresenter: SignInPresenter): SignInContract.SignInPresenter
}