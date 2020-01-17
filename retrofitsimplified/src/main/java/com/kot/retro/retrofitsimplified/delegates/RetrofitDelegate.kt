package com.kot.retro.retrofitsimplified.delegates

import com.kot.retro.retrofitsimplified.KRetrofitApi
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T> kRetrofitCreate(): ReadOnlyProperty<Any, T> =
    object : ReadOnlyProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            return KRetrofitApi.retrofit.create<T>(T::class.java)
        }
    }