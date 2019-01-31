package com.mosso.convertmoney.mainscreen.di

import com.mosso.convertmoney.core.CoreModule
import com.mosso.convertmoney.mainscreen.constract.Contract
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CurrencyModule::class, CoreModule::class])

interface CurrencyComponent{

    fun getMainPresenter(): Contract.Presenter
}