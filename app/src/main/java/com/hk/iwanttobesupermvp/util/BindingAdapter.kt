package com.hk.iwanttobesupermvp.util

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.BindingAdapter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

typealias OnRxClickListener = (View) -> Unit

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:setOnRxDebounce")
    fun setOnRxDebounce(view: View, listener: View.OnClickListener) {
        // observable로 방출해야 될 것은 debounce되고자 하는 그 행동 , 함수를 반환하고 싶은 것이다.
        Observable.create { _: ObservableEmitter<OnRxClickListener> ->
            view.setOnClickListener(listener)
        }
            .debounce(500L, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<OnRxClickListener> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: OnRxClickListener) {
                    view.run(t)
                }

                override fun onError(e: Throwable) {}

                override fun onComplete() {}
            })
    }
}