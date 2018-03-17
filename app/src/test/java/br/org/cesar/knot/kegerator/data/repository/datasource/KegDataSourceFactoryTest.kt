package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.repository.KegDataSourceFactory
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test

class KegDataSourceFactoryTest {

    private lateinit var kegDataSourceFactory: KegDataSourceFactory

    private lateinit var kegRemoteDataSource: KegRemoteDataSource

    @Before
    fun setUp() {
        kegRemoteDataSource = mock()
        kegDataSourceFactory = KegDataSourceFactory(kegRemoteDataSource)
    }

    @Test
    fun `create remote returns remote data source`() {
        val kegDataSource = kegDataSourceFactory.createRemote()
        assert(kegDataSource is KegRemoteDataSource)
    }
}