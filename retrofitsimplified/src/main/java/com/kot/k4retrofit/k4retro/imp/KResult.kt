package com.kot.k4retrofit.k4retro.imp

import com.kot.k4retrofit.k4retro.datas.APIError
import com.kot.k4retrofit.k4retro.enums.FailureType
import retrofit2.Call
import retrofit2.Response


sealed class KResult<T>{
    class Loading<T>: KResult<T>()
    data class Success<T>(val call : Call<T>, val response : Response<T>,val data : T?) : KResult<T>()
    data class Failure<T>(val call : Call<T> , val error : String?,val failureType: FailureType): KResult<T>()
    data class Error<T>(val call: Call<T> ,val apiError: APIError?) : KResult<T>()
}