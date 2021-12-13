package io.github.leonidius20.vaggregator.data.movies.providers.toloka

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TolokaRetrofitClient {

    companion object {
        val api: TolokaService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(TolokaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(TolokaService::class.java)
        }
    }

}