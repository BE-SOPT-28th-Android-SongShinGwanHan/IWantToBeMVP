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
    // 코드 리뷰 X
    @JvmStatic
    @BindingAdapter("android:setOnRxDebounce")
    fun setOnRxDebounce(view: View, listener: View.OnClickListener) {
        Observable.create { emitter: ObservableEmitter<OnRxClickListener> ->
            view.setOnClickListener {
                if (!emitter.isDisposed) {
                    emitter.onNext {
                        listener.onClick(it)
                    }
                }
            }
        }
            .debounce(500L, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<OnRxClickListener> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: OnRxClickListener) {
                    view.run(t)
                }

                override fun onError(e: Throwable) {}

                override fun onComplete() {
                }
            })
    }
}