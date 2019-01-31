package com.mosso.convertmoney.mainscreen.constract

import com.mosso.convertmoney.models.response.Currency
import io.reactivex.Observable

interface Contract {

     interface View{

      fun showLoading()
      fun hideLoading()
      fun setListCurrency(currency: Currency)
      fun showError(message:String)

     }
    interface Presenter{

        fun attempGetCurrency()
        fun onDestroy()
    }
    interface Interactor{

        fun attempGetCurrency(): Observable<Currency>
    }

    interface Repository{

        fun attempGetCurrency(): Observable<Currency>
    }



}