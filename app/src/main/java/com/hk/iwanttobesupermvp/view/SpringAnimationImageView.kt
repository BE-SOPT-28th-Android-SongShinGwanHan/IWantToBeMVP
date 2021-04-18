package com.hk.iwanttobesupermvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.view.doOnLayout
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

// 코드리뷰 X
class SpringAnimationImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private var originalY = 0f
    private var originalX = 0f

    lateinit var animationX: SpringAnimation

    lateinit var animationY: SpringAnimation

    private var deltaY = 0f
    private var deltaX = 0f

    private var translateY = 0f
    private var translateX = 0f

    init {
        doOnLayout {
            originalY = y
            originalX = x
            createAnimationX()
            createAnimationY()
            setUpTouchListener()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpTouchListener() {
        setOnTouchListener { view, event: MotionEvent? ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    deltaX = view.x - event.rawX
                    deltaY = view.y - event.rawY
                    animationY.cancel()
                    animationX.cancel()
                }
                MotionEvent.ACTION_MOVE -> {
                    translateX = deltaX + event.rawX
                    translateY = deltaY + event.rawY
                    view.x = translateX
                    view.y = translateY
                }
                MotionEvent.ACTION_UP -> {
                    animationX.start()
                    animationY.start()
                }
            }
            return@setOnTouchListener true
        }
    }

    private fun createAnimationX() {
        animationX = SpringAnimation(this, SpringAnimation.X).apply {
            spring = SpringForce().apply {
                finalPosition = originalX
                stiffness = SpringForce.STIFFNESS_MEDIUM
                dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            }
        }
    }

    private fun createAnimationY() {
        animationY = SpringAnimation(this, SpringAnimation.Y).apply {
            spring = SpringForce().apply {
                finalPosition = originalY
                stiffness = SpringForce.STIFFNESS_MEDIUM
                dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            }
        }
    }

}