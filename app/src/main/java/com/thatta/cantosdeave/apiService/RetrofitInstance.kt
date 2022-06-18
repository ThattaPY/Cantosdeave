package com.thatta.cantosdeave.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val BASE_URL = "https://xeno-canto.org/api/2/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // use lazy to insure that only one instance of retrofit will be used - no duplication
    val retrofitService: BirdsAPIService by lazy {
        getRetrofit().create(BirdsAPIService::class.java)
    }

}