package com.mosso.convertmoney.core

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.mosso.convertmoney.constans.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CoreModule {
    companion object {


        private lateinit var activity: Activity
        private lateinit var fragment: Fragment
        private lateinit var context: Context


        fun provideActivity(): Activity {
            return this.activity
        }


        fun provideFragment(): Fragment {
            return this.fragment
        }


        fun provideOKHttpClient(): OkHttpClient {

            var httpLogginIntepcertor = HttpLoggingInterceptor()
            httpLogginIntepcertor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLogginIntepcertor)
                .build()

            return okHttpClient
        }

        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val KoinCoreModule = module {

            single { provideActivity() }
            single { provideFragment() }
            single { provideOKHttpClient() }
            single { provideRetrofit(get()) }

        }
    }

}