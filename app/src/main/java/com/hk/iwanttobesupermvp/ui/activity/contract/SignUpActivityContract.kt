package com.hk.iwanttobesupermvp.ui.activity.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.hk.iwanttobesupermvp.domain.User

class SignUpActivityContract : ActivityResultContract<Intent, User>() {
    override fun createIntent(context: Context, input: Intent): Intent = input

    override fun parseResult(resultCode: Int, intent: Intent?): User? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getParcelableExtra("SignedUser")
            else -> null
        }
    }
}