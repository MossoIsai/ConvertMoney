package com.mosso.convertmoney.base

import androidx.appcompat.app.AppCompatActivity

 abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutId():Int
 }