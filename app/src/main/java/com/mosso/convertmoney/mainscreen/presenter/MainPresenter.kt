package com.mosso.convertmoney.mainscreen.presenter

import android.util.Log
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
            view?.showLoading()
            subcriptor  = interactor.attempGetCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<Currency>(){
                    override fun onComplete() {
                      view?.hideLoading()
                    }

                    override fun onNext(t: Currency) {
                        view?.setListCurrency(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG,e.message)
                         view?.showError("Ocurrio un error")
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