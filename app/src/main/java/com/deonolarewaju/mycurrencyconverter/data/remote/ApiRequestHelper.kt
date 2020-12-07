package com.deonolarewaju.mycurrencyconverter.data.remote

import com.deonolarewaju.mycurrencyconverter.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class ApiRequestHelper {

    suspend fun <T: Any> apiRequest(call: suspend() -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        }

        if (response.code() == 503){
            throw ApiException("Service Unavailable")
        } else {
            val message = StringBuilder()
            val error = response.errorBody()?.toString()

            error?.let {
                try {
                    val v = JSONObject(it).getString("success")
                    message.append((v))
                } catch (e: JSONException) { }
                message.append("\n")

            }
            message.append("Error code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}