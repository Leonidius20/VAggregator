package io.github.leonidius20.vaggregator.data.providers.tpb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ThePirateBayRetrofitClient {

    companion object {

        val api: ThePirateBayService by lazy {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(ThePirateBayService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ThePirateBayService::class.java)
        }

    }

}