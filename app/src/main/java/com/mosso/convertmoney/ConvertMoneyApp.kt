package com.mosso.convertmoney

import android.app.Application
import com.mosso.convertmoney.core.CoreModule.Companion.KoinCoreModule
import com.mosso.convertmoney.mainscreen.di.CurrencyModule.Companion.KoinCurrencyModule
import org.koin.android.ext.android.startKoin


class ConvertMoneyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(KoinCurrencyModule,KoinCoreModule))

    }



}