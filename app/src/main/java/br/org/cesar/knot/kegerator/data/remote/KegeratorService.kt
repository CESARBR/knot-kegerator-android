package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import io.reactivex.Single
import retrofit2.http.GET

interface KegeratorService {
    @GET("/kegs")
    fun fetchKegs(): Single<List<KegRemote>>
}