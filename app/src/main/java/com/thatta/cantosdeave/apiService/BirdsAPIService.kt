package com.thatta.cantosdeave.apiService

import com.thatta.cantosdeave.model.Bird
import com.thatta.cantosdeave.model.Birds
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BirdsAPIService {
    @GET("recordings?")
    suspend fun getBirdsNames(@Query("query") country: String): Response<Birds>

}