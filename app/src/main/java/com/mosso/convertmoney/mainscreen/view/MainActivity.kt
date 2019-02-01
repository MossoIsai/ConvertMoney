package com.mosso.convertmoney.mainscreen.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mosso.convertmoney.R
import com.mosso.convertmoney.base.BaseActivity
import com.mosso.convertmoney.mainscreen.constract.Contract
import com.mosso.convertmoney.models.response.TypeCurrency
import com.mosso.convertmoney.utils.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MainActivity : BaseActivity(), Contract.View {

    private lateinit var loading:LoadingDialog
    private val TAG: String  = MainActivity::class.java.simpleName
    private lateinit var currencyOrigin:String
    private lateinit var currencyDestination:String
    val presenter : Contract.Presenter by inject { parametersOf(this) }


    private val arrayCurrency =  arrayOf(    "NZD","EUR",
        "CAD", "MXN", "AUD","CNY", "PHP", "GBP", "CZK", "USD", "SEK", "NOK", "TRY", "IDR", "ZAR", "MYR", "HKD", "HUF", "ISK", "HRK", "JPY", "BGN", "SGD", "RUB", "RON", "CHF", "DKK", "INR", "KRW", "THB", "BRL", "PLN", "ILS")


    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        makeLoading()
        presenter.attempGetCurrency()
        initView()
        btnCalculate.setOnClickListener {
          presenter.attempCompareCurrencys(currencyOrigin,edtAmount.text.toString())
        }
    }

    override fun showLoading(isVisible: Boolean) {
        loading.show(isVisible)
    }

    override fun setListCurrency(typeCurrency: TypeCurrency) {
        Log.d(TAG,"setListCurrency: $typeCurrency")

    }

    override fun setResult(resultCompare: String) {
        Log.d("TAG","setResult-> result: $resultCompare")
        tvtResult.text = resultCompare
    }

    override fun showError(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }

    fun setupInjection(){
       /*presenter = (applicationContext as ConvertMoneyApp)
           .getComponent(this, this)
           .getMainPresenter()*/
    }

    fun  initView(){
        spnOrigin.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayCurrency)
        spnDestination.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayCurrency)

        spnOrigin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                currencyOrigin = arrayCurrency[position]

            }
        }

        spnDestination.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                currencyDestination = arrayCurrency[position]

            }
        }
    }

    fun makeLoading(){
        loading = LoadingDialog.getInstance(this);
        loading.show(true)

    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter.onDestroy()
    }



}
