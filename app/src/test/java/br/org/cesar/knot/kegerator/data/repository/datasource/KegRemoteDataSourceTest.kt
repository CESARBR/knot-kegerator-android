package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.repository.model.KegData
import br.org.cesar.knot.kegerator.test.KegDataFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class KegRemoteDataSourceTest {

    private lateinit var kegRemoteDataSource: KegRemoteDataSource

    private lateinit var kegApi: KegApi

    @Before
    fun setUp() {
        kegApi = mock()
        kegRemoteDataSource = KegRemoteDataSource(kegApi)
    }

    @Test
    fun `listing kegs completes`() {
        stubKegRemoteDataSourceList(Single.just(
                KegDataFactory.makeKegDataList(2)
        ))
        val testObserver = kegRemoteDataSource.list().test()
        testObserver.assertComplete()
    }

    @Test
    fun `listing kegs returns data`() {
        val kegs = KegDataFactory.makeKegDataList(2)
        stubKegRemoteDataSourceList(Single.just(kegs))
        val testObserver = kegRemoteDataSource.list().test()
        testObserver.assertValue(kegs)
    }

    fun stubKegRemoteDataSourceList(single: Single<List<KegData>>) {
        whenever(kegApi.fetchKegs()).thenReturn(single)
    }

}