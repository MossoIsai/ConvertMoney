package com.mosso.convertmoney.api.endpoints

import com.mosso.convertmoney.models.response.Currency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointMain{

    @GET("latest")
    fun getCurrencys():Observable<Currency>

    @GET("latest")
    fun compare(@Query("base") base:String):Observable<Currency>

}

