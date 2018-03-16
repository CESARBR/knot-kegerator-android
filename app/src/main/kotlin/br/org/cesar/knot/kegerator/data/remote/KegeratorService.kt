package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.domain.model.Keg
import retrofit2.Call
import retrofit2.http.GET

interface KegeratorService {
    @GET("/kegs")
    fun fetchKegs(): Call<List<Keg>>
}