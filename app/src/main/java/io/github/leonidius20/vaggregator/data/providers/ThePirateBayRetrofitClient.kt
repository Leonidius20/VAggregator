package io.github.leonidius20.vaggregator.data.providers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ThePirateBayRetrofitClient {

    companion object {

        val instance: ThePirateBayRetrofitClient by lazy { ThePirateBayRetrofitClient() }

    }

    val api: ThePirateBayService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ThePirateBayService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(ThePirateBayService::class.java)
    }

}