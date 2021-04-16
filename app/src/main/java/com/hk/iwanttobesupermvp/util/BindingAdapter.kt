package com.hk.iwanttobesupermvp.util

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
        // view는 뭐 버튼이나 그런 것들
        // listener는 이제 databinding으로 올 presenter의 행동
        // 안되는거 같은데 잠시
        Observable.create { _: ObservableEmitter<OnRxClickListener> -> }
            .debounce(2000L, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<OnRxClickListener> {
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: OnRxClickListener) {
                    view.setOnClickListener {
                        listener.onClick(it)
                    }
                    view.run(t)
                }
                override fun onError(e: Throwable) {}

                override fun onComplete() {
                }
            })
    }
}