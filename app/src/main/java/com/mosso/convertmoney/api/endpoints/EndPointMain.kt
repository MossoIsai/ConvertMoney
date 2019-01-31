package com.mosso.convertmoney.api.endpoints

import com.mosso.convertmoney.models.response.Currency
import io.reactivex.Observable
import retrofit2.http.GET

interface EndPointMain{

    @GET("latest")
    fun getCurrencys():Observable<Currency>

}

