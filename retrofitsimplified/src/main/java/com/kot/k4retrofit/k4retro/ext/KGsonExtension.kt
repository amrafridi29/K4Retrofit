package com.kot.k4retrofit.k4retro.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.toJson() : String{
    val gson = Gson()
    val type = object : TypeToken<Any>(){}.type
    return gson.toJson(this , type)
}

inline fun<reified T> String.toObject() : T{
    return Gson().fromJson(this , T::class.java)
}

inline fun<reified T> String.toObjects() : T{
    val gson = Gson()
    val type= object : TypeToken<T>(){}.type
    return gson.fromJson<T>(this , type)
}