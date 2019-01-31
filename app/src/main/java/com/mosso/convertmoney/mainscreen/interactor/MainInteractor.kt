package com.mosso.convertmoney.mainscreen.interactor

import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.models.response.Currency
import io.reactivex.Observable

class MainInteractor : Contract.Interactor{

    private  var repository: Contract.Repository

    constructor(repository: Contract.Repository){
        this.repository = repository
    }

    override fun attempGetCurrency(): Observable<Currency> {
        return  repository.attempGetCurrency()
    }


}