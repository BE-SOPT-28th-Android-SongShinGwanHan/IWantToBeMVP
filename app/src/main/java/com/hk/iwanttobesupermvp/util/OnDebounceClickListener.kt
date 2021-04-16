package com.hk.iwanttobesupermvp.util

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * typealias는 일종에 별명을 변수에게 쥐어주는 것이다.
 * 왜 이런걸 하나 싶은 생각이 들 수도 있겠지만 생각한 번 해보아라
 * 저 OnClickListener라는 별명을 가진 친구는 본디 함수다
 * 인풋으로 View를 받고 Unit을 반환하는 형태의 함수이며
 * 이 함수는 View.OnClickListener에 들어가는 함수 원형 이기도 하다
 * 그런 함수를 그저 단순하게 val originalFunction = (View) -> Unit 이라고 하기엔 뭔가 가독성도 불편하고 그렇다
 * 그래서 이러한 typealias를 사용한다.
 */

/**
 * debounce의 개념은 event가 하나 들어왔을때 특정시간 동안 동일한 이벤트가 도달하게 된다면??
 * 그 동일한 이벤트들을 특정시간대로 한묶음시켜 하나의 이벤트가 발생한것으로 해주는 것이다.
 */
typealias OnClickListener = (View) -> Unit

class OnDebounceClickListener(private val listener: OnClickListener) : View.OnClickListener {
    override fun onClick(v: View?) {
        val now = System.currentTimeMillis() // click이벤트가 들어온 순간의 시간을 저장
        if (now < lastTime + INTERVAL) return // 만약 , 순간의 시간동안에 다른 입력이 들어왔는데 그것이 lastTime + Interval보다 작다 그렇다면 반환
        lastTime = now // 아니라면 lastTime에 이벤트가 들어왔던 시간을 넣어준후 listener를 실행시켜준다.
        v?.run(listener)
    }

    companion object {
        private const val INTERVAL: Long = 500L
        private var lastTime: Long = 0
    }
}

infix fun View.setOnDebounceClickListener(listener: OnClickListener) {
    this.setOnClickListener(OnDebounceClickListener {
        it.run(listener)
    })
}

@BindingAdapter("android:onDebounceClick")
infix fun View.setOnDebounceClickListener(listener: View.OnClickListener) {
    if (listener == null) {
        this.setOnClickListener(null)
    } else {
        this.setOnClickListener(OnDebounceClickListener {
            listener.onClick(it)
        })
    }
}


