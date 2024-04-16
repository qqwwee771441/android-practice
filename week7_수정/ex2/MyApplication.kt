package com.choi.myapplication

import android.app.Application
//import com.android.volley.toolbox.Volley
import com.choi.myapplication.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {
    companion object {

        //QUERY 전송을 위한 요구 데이터
        val QUERY = "travel"
        val API_KEY = "dfb72098202545d795e8076048417a59"    //https://newsapi.org/ 에서 KEY 발급받기
        val BASE_URL = "https://newsapi.org"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"

        var networkService: NetworkService

        //retrofit Builder 선언
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            //retrofit initialization
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

}

