package br.org.cesar.knot.kegerator.data.repository

import br.org.cesar.knot.kegerator.data.KegGateway
import br.org.cesar.knot.kegerator.data.repository.datasource.KegRemoteDataSource
import br.org.cesar.knot.kegerator.data.repository.mapper.KegMapper
import br.org.cesar.knot.kegerator.data.repository.model.KegData
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.test.KegDataFactory
import br.org.cesar.knot.kegerator.test.KegFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class KegGatewayTest {

    private lateinit var kegGateway: KegGateway

    private lateinit var kegMapper: KegMapper
    private lateinit var kegRemoteDataSource: KegRemoteDataSource

    @Before
    fun setUp() {
        kegMapper = mock()
        kegRemoteDataSource = mock()
        kegGateway = KegGateway(kegRemoteDataSource, kegMapper)
    }

    //<editor-fold desc="list Kegs">
    @Test
    fun `listing kegs completes`() {
        stubKegRemoteDataSourceList(Single.just(
                KegDataFactory.makeKegDataList(2)
        ))
        val testObserver = kegGateway.list().test()
        testObserver.assertComplete()
    }

    @Test
    fun `listing kegs returns data`() {
        val kegs = KegFactory.makeKegList(2)
        val kegEntities = KegDataFactory.makeKegDataList(2)
        kegs.forEachIndexed { index, keg ->
            stubKegMapperMapFromData(kegEntities[index], keg)
        }
        stubKegRemoteDataSourceList(Single.just(kegEntities))
        val testObserver = kegGateway.list().test()
        testObserver.assertValue(kegs)
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubKegRemoteDataSourceList(single: Single<List<KegData>>) {
        whenever(kegRemoteDataSource.list())
                .thenReturn(single)
    }

    private fun stubKegMapperMapFromData(kegData: KegData, keg: Keg) {
        whenever(kegMapper.mapFromData(kegData))
                .thenReturn(keg)
    }
    //</editor-fold>

}