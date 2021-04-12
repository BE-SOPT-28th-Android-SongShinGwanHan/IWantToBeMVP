package com.hk.iwanttobesupermvp.ui.activity.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.hk.iwanttobesupermvp.domain.User

class SignUpActivityContract : ActivityResultContract<Intent, User>() {
    // Intent를 만드는 함수 , 인자를 넘겨주는 것이 없다면 launch를 하면서 intent를 넘기게 되는데 그 경우 이미 intent를 받았기 때문에 input을 반환하면 된다
    // 다른 액티비티를 실행할때 특정 값을 전달해서 실행하게 되면 intent를 이 메소드에서 만들어서 반환시켜준다.
    override fun createIntent(context: Context, input: Intent): Intent = input

    // 다른 액티비티가 실행되고 끝날 때의 처리를 담당하는 메소드
    override fun parseResult(resultCode: Int, intent: Intent?): User? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getParcelableExtra("SignedUser")
            else -> null // 처리상으로 좋지는 않은거에요!
        }
    }
}