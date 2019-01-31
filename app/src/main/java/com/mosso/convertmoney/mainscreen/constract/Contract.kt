package com.mosso.convertmoney.mainscreen.constract

import com.mosso.convertmoney.models.response.Currency
import com.mosso.convertmoney.models.response.TypeCurrency
import io.reactivex.Observable

interface Contract {

     interface View{

      fun showLoading( isVisible: Boolean)
      fun setListCurrency(currency: TypeCurrency)
      fun showError(message:String)
      fun setResult(resultCompare:String)

     }
    interface Presenter{

        fun attempGetCurrency()
        fun attempCompareCurrencys(firstCurrency: String, secondCurrency: String)
        fun onDestroy()
    }
    interface Interactor{

        fun attempGetCurrency(): Observable<Currency>
        fun attempCompareCurrencys(unionCurrency: String) : Observable<Currency>

    }

    interface Repository{

        fun attempGetCurrency(): Observable<Currency>
        fun attempCompareCurrencys(unionCurrency: String) : Observable<Currency>

    }



}