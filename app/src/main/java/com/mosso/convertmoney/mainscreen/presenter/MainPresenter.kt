package com.mosso.convertmoney.mainscreen.presenter

import android.util.Log
import com.google.gson.Gson
import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.models.response.Currency
import com.mosso.convertmoney.models.response.TypeCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter: Contract.Presenter  {



    private var view: Contract.View? = null
    private  var interactor: Contract.Interactor
    private var subcriptor: Disposable? = null
    private val TAG = MainPresenter::class.java.simpleName



    constructor(view: Contract.View, interactor: Contract.Interactor){
        this.view = view
        this.interactor = interactor
    }

    override fun attempGetCurrency() {
        view.let {
            view?.showLoading(true)
            subcriptor = interactor.attempGetCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Currency>() {
                    override fun onComplete() {
                        view?.showLoading(false)
                    }

                    override fun onNext(t: Currency) {
                        t.rates?.let { view?.setListCurrency(it) }
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, e.message)
                        view?.showError("Problemas de conexión...")
                        view?.showLoading(false)
                    }
                })
        }

    }

    override fun attempCompareCurrencys(baseCurrency: String, amount:String) {
        if (!amount.equals("")) {
            Log.d(TAG, "monto->: $amount")
            val amountDouble:Double = amount.toDouble()
            view.let{
                view?.showLoading(true)
                subcriptor = interactor.attempCompareCurrencys(baseCurrency)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<Currency>() {
                        override fun onComplete() {
                            view?.showLoading(false)
                        }

                        override fun onNext(t: Currency) {
                            val currencyDouble: Double? = t.rates?.mxn?.toDouble()
                            val result = currencyDouble?.times(amountDouble)

                            view?.setResult("$ $result")
                        }

                        override fun onError(e: Throwable) {
                            view?.showError("Ocurrio un error--->")
                            view?.showLoading(false)
                        }

                    })

            }
        }else{
            view?.showError("El necesario llenar el campo de cantidad")
        }
    }




    override fun onDestroy() {
        subcriptor?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

}