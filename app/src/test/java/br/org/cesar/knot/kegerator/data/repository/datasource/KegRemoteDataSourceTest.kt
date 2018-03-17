package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import br.org.cesar.knot.kegerator.test.KegEntityFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert.*
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
                KegEntityFactory.makeKegEntityList(2)
        ))
        val testObserver = kegRemoteDataSource.list().test()
        testObserver.assertComplete()
    }

    @Test
    fun `listing kegs returns data`() {
        val kegEntities = KegEntityFactory.makeKegEntityList(2)
        stubKegRemoteDataSourceList(Single.just(kegEntities))
        val testObserver = kegRemoteDataSource.list().test()
        testObserver.assertValue(kegEntities)
    }

    fun stubKegRemoteDataSourceList(single: Single<List<KegEntity>>) {
        whenever(kegApi.fetchKegs())
            .thenReturn(single)
    }

}