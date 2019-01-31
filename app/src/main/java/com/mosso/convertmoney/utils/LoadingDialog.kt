package com.mosso.convertmoney.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.mosso.convertmoney.R

class LoadingDialog( context: Context){


    companion object {
        var loadingDialog: LoadingDialog? = null
        private var progressDialog: ProgressDialog? =  null

        fun getInstance(context: Context):LoadingDialog{
            if(loadingDialog ==  null){
                loadingDialog = LoadingDialog(context)
                progressDialog = buildProgressDialog(context)
            }
            return loadingDialog as LoadingDialog
        }


        private fun buildProgressDialog(context: Context): ProgressDialog {
            val progressDialog = ProgressDialog(context)
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            progressDialog.setCancelable(false)
            progressDialog.show()
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog.setContentView(LayoutInflater.from(context).inflate(R.layout.progress_dialog, null))
            return progressDialog
        }
    }

    fun show( isVisible: Boolean){
        if(isVisible){
            println("ProgressDialog MOSTRANDO")
            progressDialog?.show()
        }else{
            print("ProgressDialog OCULTAR")
            progressDialog?.dismiss()
        }
    }
}