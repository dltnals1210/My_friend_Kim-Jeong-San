package com.han.my_friend_kim_jung_san

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.han.my_friend_kim_jung_san.config.XAccessTokenInterceptor
import com.kakao.sdk.common.KakaoSdk
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApplicationClass: Application() {
    companion object{
        const val X_ACCESS_TOKEN: String = "X-ACCESS-TOKEN"         // JWT Token Key
        const val TAG: String = "JEONG-SAN-APP"                      // Log, SharedPreference
        const val APP_DATABASE = "$TAG-DB"

        const val DEV_URL: String = "http://dev.kimjeongsan.shop"
        const val PROD_URL: String = "http://prod.kimjeongsan.shop"
        const val BASE_URL: String = DEV_URL

        lateinit var mSharedPreferences: SharedPreferences
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mSharedPreferences = applicationContext.getSharedPreferences(TAG, Context.MODE_PRIVATE)
    }
}