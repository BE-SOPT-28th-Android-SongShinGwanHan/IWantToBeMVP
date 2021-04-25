package com.hk.iwanttobesupermvp.util

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ActivityBindingAutoCleared<T : Any> : ReadWriteProperty<Activity, T>,LifecycleObserver{
    private var _binding : T? = null

    override fun setValue(thisRef: Activity, property: KProperty<*>, value: T) {
        _binding = value
    }

    override fun getValue(thisRef: Activity, property: KProperty<*>): T = _binding ?: throw IllegalArgumentException("can't get value outside of lifecycle")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        _binding = null
    }
}