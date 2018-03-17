package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.data.remote.mapper.KegEntityMapper
import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import br.org.cesar.knot.kegerator.test.KegEntityFactory
import br.org.cesar.knot.kegerator.test.KegRemoteFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RestApiTest {

    private lateinit var restApi: RestApi

    private lateinit var kegeratorService: KegeratorService
    private lateinit var kegEntityMapper: KegEntityMapper

    @Before
    fun setUp() {
        kegEntityMapper = mock()
        kegeratorService = mock()
        restApi = RestApi(kegeratorService, kegEntityMapper)
    }

    @Test
    fun `fetching kegs completes`() {
        stubKegeratorServiceFetchKegs(Single.just(
                KegRemoteFactory.makeKegRemoteList(2)
        ))
        val testObserver = restApi.fetchKegs().test()
        testObserver.assertComplete()
    }

    @Test
    fun `listing kegs returns data`() {
        val kegEntities = KegEntityFactory.makeKegEntityList(2)
        val kegRemotes = KegRemoteFactory.makeKegRemoteList(2)
        kegEntities.forEachIndexed { index, kegEntity ->
            stubKegEntityMapperMapFromRemote(kegRemotes[index], kegEntity)
        }
        stubKegeratorServiceFetchKegs(Single.just(kegRemotes))
        val testObserver = restApi.fetchKegs().test()
        testObserver.assertValue(kegEntities)
    }

    fun stubKegeratorServiceFetchKegs(single: Single<List<KegRemote>>) {
        whenever(kegeratorService.fetchKegs())
            .thenReturn(single)
    }

    private fun stubKegEntityMapperMapFromRemote(kegRemote: KegRemote, kegEntity: KegEntity) {
        whenever(kegEntityMapper.mapFromRemote(kegRemote))
            .thenReturn(kegEntity)
    }

}