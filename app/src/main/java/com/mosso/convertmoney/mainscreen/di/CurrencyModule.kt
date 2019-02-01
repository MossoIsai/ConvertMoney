package com.mosso.convertmoney.mainscreen.di

import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.mainscreen.interactor.MainInteractor
import com.mosso.convertmoney.mainscreen.presenter.MainPresenter
import com.mosso.convertmoney.mainscreen.repository.MainRepository
import org.koin.dsl.module.module
import retrofit2.Retrofit

class CurrencyModule{
    companion object {


    private lateinit var view: Contract.View



    fun provideView():Contract.View{
       return  this.view
    }

    fun providerPresenter(view: Contract.View, interactor: Contract.Interactor):Contract.Presenter{
        return MainPresenter(view, interactor)
    }


    fun providerInteractor(repository: Contract.Repository): Contract.Interactor{
        return MainInteractor(repository)
    }


    fun providerRepository(retrofit: Retrofit):Contract.Repository{
        return MainRepository(retrofit)
    }

     val KoinCurrencyModule = module {

             single { provideView() }
             single { providerRepository(get()) }
             single { providerInteractor(get())}
             factory<Contract.Presenter> {(currencyView : Contract.View) ->
                 MainPresenter(currencyView, get())
             }

       }
     }


    }