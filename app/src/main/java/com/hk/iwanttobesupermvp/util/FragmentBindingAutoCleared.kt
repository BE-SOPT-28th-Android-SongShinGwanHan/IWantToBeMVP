package com.hk.iwanttobesupermvp.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentBindingAutoCleared<T : Any> : ReadWriteProperty<Fragment,T>,LifecycleObserver{
    private var _binding : T? = null

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        thisRef.viewLifecycleOwner.lifecycle.removeObserver(this)
        _binding = value
        thisRef.viewLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T = _binding ?: throw IllegalArgumentException("can't get value outside lifecycle")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        _binding = null
    }
}