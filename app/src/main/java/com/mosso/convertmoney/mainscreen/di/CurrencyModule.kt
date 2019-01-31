package com.mosso.convertmoney.mainscreen.di

import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.mainscreen.interactor.MainInteractor
import com.mosso.convertmoney.mainscreen.presenter.MainPresenter
import com.mosso.convertmoney.mainscreen.repository.MainRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CurrencyModule(){

    private lateinit var view: Contract.View

    constructor(view:Contract.View) : this() {
        this.view =  view
    }

    @Provides
    @Singleton
    fun provideView():Contract.View{
       return  this.view
    }


    @Provides
    @Singleton
    fun providerPresenter(view: Contract.View, interactor: Contract.Interactor):Contract.Presenter{
        return MainPresenter(view, interactor)
    }

    @Provides
    @Singleton
    fun providerInteractor(repository: Contract.Repository): Contract.Interactor{
        return MainInteractor(repository)
    }

    @Provides
    @Singleton
    fun providerRepository(retrofit: Retrofit):Contract.Repository{
        return MainRepository(retrofit)
    }





}