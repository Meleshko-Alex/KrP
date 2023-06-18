package com.example.krp.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("1x8aVaOPaO-jwaj3NNaamJ-sRVfmFvHgjmtEmo_FmVgo/values/Список не прибув. до ТЦК!A2:G11?key=AIzaSyAB7sLUhFx_fdW-ZoLV-YfKG_vh5fmKQmY")
    suspend fun getBaseTable(): Response<String>

    companion object {
        private const val BASE_URL = "https://sheets.googleapis.com/v4/spreadsheets/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}