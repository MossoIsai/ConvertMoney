package com.mosso.convertmoney.core

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.mosso.convertmoney.constans.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CoreModule {

    private lateinit var activity: Activity
    private lateinit var fragment: Fragment
    private lateinit var context: Context


    constructor(activity: Activity) {
        this.activity = activity
        this.context = this.activity.applicationContext
    }

    @Provides
    @Singleton
    fun provideActivity(): Activity {
        return this.activity
    }

    @Provides
    @Singleton
    fun provideFragment(): Fragment {
        return this.fragment
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {

        var httpLogginIntepcertor = HttpLoggingInterceptor()
        httpLogginIntepcertor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient  = OkHttpClient.Builder()
            .connectTimeout(Constants.CONNECTION_TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(Constants.CONNECTION_TIMEOUT,TimeUnit.SECONDS)
            .addInterceptor(httpLogginIntepcertor)
            .build()

        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}