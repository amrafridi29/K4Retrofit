package com.kot.k4retrofit.k4retro.delegates

import com.kot.k4retrofit.k4retro.KRetrofitApi
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T> kRetrofitCreate(): ReadOnlyProperty<Any, T> =
    object : ReadOnlyProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            return KRetrofitApi.retrofit.create<T>(T::class.java)
        }
    }