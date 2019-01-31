package com.mosso.convertmoney.mainscreen.presenter

import android.util.Log
import com.google.gson.Gson
import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.models.response.Currency
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
        if (view != null) {
            view?.showLoading(true)
            subcriptor  = interactor.attempGetCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<Currency>(){
                    override fun onComplete() {
                      view?.showLoading(false)
                    }
                    override fun onNext(t: Currency) {
                        t.rates?.let { view?.setListCurrency(it) }
                    }
                    override fun onError(e: Throwable) {
                        Log.d(TAG,e.message)
                         view?.showError("Problemas de conexión...")
                         view?.showLoading(false)
                    }
                })
        }

    }

    override fun attempCompareCurrencys(firstCurrency: String, secondCurrency: String){
        val unionCurrency = "$firstCurrency,$secondCurrency "
        if (view != null){
            view?.showLoading(true)
            subcriptor = interactor.attempCompareCurrencys(unionCurrency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Currency>(){
                    override fun onComplete() {
                        view?.showLoading(false)
                    }

                    override fun onNext(t: Currency) {

                    }

                    override fun onError(e: Throwable) {
                        view?.showError("Ocurrio un error--->")
                        view?.showLoading(false)
                    }

                })
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