package com.mosso.convertmoney

import android.app.Application
import com.mosso.convertmoney.core.CoreModule
import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.mainscreen.di.CurrencyComponent
import com.mosso.convertmoney.mainscreen.di.CurrencyModule
import com.mosso.convertmoney.mainscreen.di.DaggerCurrencyComponent
import com.mosso.convertmoney.mainscreen.view.MainActivity


class ConvertMoneyApp: Application() {

    override fun onCreate() {
        super.onCreate()

    }

    fun  getComponent(activity: MainActivity, view: Contract.View): CurrencyComponent {

        return DaggerCurrencyComponent
            .builder()
            .coreModule(CoreModule(activity))
            .currencyModule(CurrencyModule(view))
            .build()
    }

}