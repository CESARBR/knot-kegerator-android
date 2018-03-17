package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import br.org.cesar.knot.kegerator.domain.model.Keg
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface KegeratorService {
    @GET("/kegs")
    fun fetchKegs(): Single<List<KegRemote>>
}