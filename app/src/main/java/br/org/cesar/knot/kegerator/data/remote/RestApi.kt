package br.org.cesar.knot.kegerator.data.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestApi {

    val BASE_URL: String = "kegerator-backend"

    val service: KegeratorService

    init {
        val retro = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient())
                .build()
        service = retro.create(KegeratorService::class.java)
    }
}