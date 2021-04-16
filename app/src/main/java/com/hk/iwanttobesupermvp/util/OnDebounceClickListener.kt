package com.hk.iwanttobesupermvp.util

import android.view.View
import androidx.databinding.BindingAdapter

/**
 *
 */
typealias OnClickListener = (View) -> Unit

class OnDebounceClickListener(private val listener : OnClickListener) : View.OnClickListener {
    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()
        if (now < lastTime + INTERVAL) return
        lastTime = now
        v?.run(listener)
    }

    companion object {
        private const val INTERVAL : Long = 500L
        private var lastTime : Long = 0
    }
}

infix fun View.setOnDebounceClickListener(listener : OnClickListener) {
    this.setOnClickListener(OnDebounceClickListener{
        it.run(listener)
    })
}

@BindingAdapter("android:onDebounceClick")
infix fun View.setOnDebounceClickListener(listener : View.OnClickListener){
    if(listener == null){
        this.setOnClickListener(null)
    } else {
        this.setOnClickListener(OnDebounceClickListener {
            listener.onClick(it)
        })
    }
}


