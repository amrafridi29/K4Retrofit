package com.kot.k4retrofit.k4retro.ext

import androidx.lifecycle.MutableLiveData
import com.kot.k4retrofit.k4retro.KRetrofitApi
import com.kot.k4retrofit.k4retro.enums.FailureType
import com.kot.k4retrofit.k4retro.imp.KResult
import com.kot.k4retrofit.k4retro.utils.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

inline fun <reified T> Call<T>.enqueue() : MutableLiveData<KResult<T>>{
    val result = MutableLiveData<KResult<T>>()
    result.value = KResult.Loading()
    enqueue(object : Callback<T>{
        override fun onFailure(call: Call<T>, t: Throwable) {
            result.value= KResult.Failure(call, t.message , when(t){
                is IOException-> FailureType.NETWORK
                else-> FailureType.CONVERSION
            })
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if(response.isSuccessful)
                result.value= KResult.Success(call, response , response.body() as T)
            else{
                result.value= KResult.Error(call , ErrorUtils.parseError(response))
            }
        }
    })

    return result
}

inline fun <reified T> Call<T>.process(crossinline OnResult : (KResult<T>)-> Unit)  {

    OnResult.invoke(KResult.Loading())
    enqueue(object : Callback<T>{
        override fun onFailure(call: Call<T>, t: Throwable) {
           OnResult.invoke(
               KResult.Failure(call, t.message , when(t){
               is IOException-> FailureType.NETWORK
               else-> FailureType.CONVERSION
           }))
        }
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if(response.isSuccessful)
               OnResult.invoke( KResult.Success(call, response , response.body() as T))
            else{
               OnResult.invoke(KResult.Error(call , ErrorUtils.parseError(response)))
            }
        }
    })

}

inline fun <reified T> Call<T>.run(crossinline OnResult: (KResult<T>) -> Unit)  {
    try{
        val response = execute()
        if(response.isSuccessful)
             OnResult.invoke(KResult.Success(this , response , response.body() as T))
        else
           OnResult.invoke( KResult.Error(this , ErrorUtils.parseError(response)))
    }catch (e : Exception){
       OnResult.invoke( KResult.Failure(this , e.message , when(e){
           is IOException-> FailureType.NETWORK
           else-> FailureType.CONVERSION
       }))
    }
}




inline fun <reified T> KRetrofitApi.create() : T{
    return retrofit.create(T::class.java)
}



