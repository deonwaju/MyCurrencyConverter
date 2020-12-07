package com.deonolarewaju.mycurrencyconverter.data.remote.retrofit

import com.deonolarewaju.mycurrencyconverter.data.model.FixerResponseModel
import com.deonolarewaju.mycurrencyconverter.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET("api/latest")
    suspend fun getLatestExchangeRates(
        @Query(value = "access_key") apiKey: String = Constants.Keys.API_KEY,
        @Query(value = "symbols") symbols: String = "USD,AUD,CAD,PLN,MXN,NGN"
    ): Response<FixerResponseModel>

    @GET("api/{date}")
    suspend fun getCurrencyByDate(
        @Path(value = "date") date: String,
        @Query(value = "access_key") apiKey: String = Constants.Keys.API_KEY,
        @Query(value = "symbols") symbols: String = "USD,AUD,CAD,PLN,MXN,NGN"
    ): Response<FixerResponseModel>

    @GET("api/convert")
    suspend fun getConvertedResult(
        @Query(value = "access_key") apiKey: String,
        @Query(value = "from") convertFrom: String,
        @Query(value = "to") convertTo: String,
        @Query(value = "amount") amount: String
    )
}