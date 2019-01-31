package com.mosso.convertmoney.mainscreen.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mosso.convertmoney.ConvertMoneyApp
import com.mosso.convertmoney.R
import com.mosso.convertmoney.base.BaseActivity
import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.models.response.Currency

class MainActivity : BaseActivity(), Contract.View {

    private lateinit var presenter:Contract.Presenter
    private val TAG: String  = MainActivity::class.java.simpleName

    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setupInjection()
        presenter.attempGetCurrency()
    }


    override fun showLoading() {
      Log.d(TAG,"showLoading")
    }

    override fun hideLoading() {
       Log.d(TAG,"hideLoading")
    }

    override fun setListCurrency(currency: Currency) {
        Log.d(TAG,"setListCurrency: $currency")

    }

    override fun showError(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }


    fun setupInjection(){
       presenter = (applicationContext as ConvertMoneyApp)
           .getComponent(this, this)
           .getMainPresenter();

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}
