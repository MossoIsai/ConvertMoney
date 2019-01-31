package com.mosso.convertmoney.mainscreen.repository

import com.mosso.convertmoney.api.endpoints.EndPointMain
import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.models.response.Currency
import io.reactivex.Observable
import retrofit2.Retrofit

class MainRepository: Contract.Repository {

   private   var retrofitClient:Retrofit

    constructor(retrofitClient: Retrofit){
        this.retrofitClient = retrofitClient
    }

    override fun attempGetCurrency():Observable<Currency> {
      return retrofitClient.create(EndPointMain::class.java).getCurrencys()
    }

}