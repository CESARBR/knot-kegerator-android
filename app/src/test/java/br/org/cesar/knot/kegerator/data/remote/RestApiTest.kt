package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.data.remote.mapper.KegDataMapper
import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.data.repository.model.KegData
import br.org.cesar.knot.kegerator.test.KegDataFactory
import br.org.cesar.knot.kegerator.test.KegRemoteFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RestApiTest {

    private lateinit var restApi: RestApi

    private lateinit var kegeratorService: KegeratorService
    private lateinit var kegDataMapper: KegDataMapper

    @Before
    fun setUp() {
        kegDataMapper = mock()
        kegeratorService = mock()
        restApi = RestApi(kegeratorService, kegDataMapper)
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
        val kegs = KegDataFactory.makeKegDataList(2)
        val kegRemotes = KegRemoteFactory.makeKegRemoteList(2)
        kegs.forEachIndexed { index, kegData ->
            stubKegEntityMapperMapFromRemote(kegRemotes[index], kegData)
        }
        stubKegeratorServiceFetchKegs(Single.just(kegRemotes))
        val testObserver = restApi.fetchKegs().test()
        testObserver.assertValue(kegs)
    }

    private fun stubKegeratorServiceFetchKegs(single: Single<List<KegRemote>>) {
        whenever(kegeratorService.fetchKegs())
            .thenReturn(single)
    }

    private fun stubKegEntityMapperMapFromRemote(kegRemote: KegRemote, kegData: KegData) {
        whenever(kegDataMapper.mapFromRemote(kegRemote))
            .thenReturn(kegData)
    }

}