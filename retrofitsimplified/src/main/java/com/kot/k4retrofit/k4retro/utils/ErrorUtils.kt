

package com.kot.k4retrofit.k4retro.utils

import com.kot.k4retrofit.k4retro.KRetrofitApi
import com.kot.k4retrofit.k4retro.datas.APIError
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