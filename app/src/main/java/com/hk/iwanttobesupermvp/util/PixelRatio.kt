package com.hk.iwanttobesupermvp.util

import android.app.Application
import androidx.annotation.Px
import com.hk.iwanttobesupermvp.application.MainApplication
import kotlin.math.roundToInt

// 코드리뷰 X
class PixelRatio(private val app: Application) {
    private val displayMetrics
        get() = app.resources.displayMetrics

    // pixel
    val screenWidth
        get() = displayMetrics.widthPixels

    val screenHeight
        get() = displayMetrics.heightPixels

    // coerceAtMost -> 지정된 최대값보다 크지 않도록 해준다.
    // 즉 최대 값보다 작거나 같으면 이 값을 반환한다.
    val screenShort
        get() = screenWidth.coerceAtMost(screenHeight)

    // coerceAtLeast -> 지정된 최소값보다 작지 않도록 해준다.
    // 최소값 또는 최소값보다 크거나 같으면 이 값을 반환
    val screenLong
        get() = screenWidth.coerceAtLeast(screenHeight)

    @Px
    fun dpToPixel(dp: Int) = (dp * displayMetrics.density).roundToInt()
    fun dpToPixelForFloatDp(dp: Float) = (dp * displayMetrics.density).roundToInt()
}

val Number.dpToPixel: Int
    get() = MainApplication.pixelRatio.dpToPixel(this.toInt())

val Number.dpToPixelFloat: Float
    get() = MainApplication.pixelRatio.dpToPixelForFloatDp(this.toFloat()).toFloat()