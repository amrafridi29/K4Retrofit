

package com.kot.retro.retrofitsimplified.utils

import com.kot.retro.retrofitsimplified.KRetrofitApi
import com.kot.retro.retrofitsimplified.datas.APIError
import retrofit2.Response
import java.io.IOException

object ErrorUtils {
    fun parseError(response: Response<*>): APIError? {
        val converter = KRetrofitApi.retrofit
            .responseBodyConverter<APIError>(
                APIError::class.java,
                arrayOfNulls(0)
            )
        val error: APIError?
        error = try {
            converter.convert(response.errorBody())
        } catch (e: IOException) {
            return APIError()
        }
        return error
    }
}