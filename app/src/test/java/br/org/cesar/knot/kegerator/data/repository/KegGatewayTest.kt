package br.org.cesar.knot.kegerator.data.repository

import br.org.cesar.knot.kegerator.data.KegGateway
import br.org.cesar.knot.kegerator.data.repository.datasource.KegRemoteDataSource
import br.org.cesar.knot.kegerator.data.repository.mapper.KegMapper
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.test.KegEntityFactory
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

    private lateinit var kegDataSourceFactory: KegDataSourceFactory
    private lateinit var kegMapper: KegMapper
    private lateinit var kegRemoteDataSource: KegRemoteDataSource

    @Before
    fun setUp() {
        kegDataSourceFactory = mock()
        kegMapper = mock()
        kegRemoteDataSource = mock()
        kegGateway = KegGateway(kegDataSourceFactory, kegMapper)
        stubKegDataSourceFactoryCreateRemote()
    }

    //<editor-fold desc="list Kegs">
    @Test
    fun `listing kegs completes`() {
        stubKegRemoteDataSourceList(Single.just(
                KegEntityFactory.makeKegEntityList(2)
        ))
        val testObserver = kegGateway.list().test()
        testObserver.assertComplete()
    }

    @Test
    fun `listing kegs returns data`() {
        val kegs = KegFactory.makeKegList(2)
        val kegEntities = KegEntityFactory.makeKegEntityList(2)
        kegs.forEachIndexed { index, keg ->
            stubKegMapperMapFromEntity(kegEntities[index], keg)
        }
        stubKegRemoteDataSourceList(Single.just(kegEntities))
        val testObserver = kegGateway.list().test()
        testObserver.assertValue(kegs)
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubKegRemoteDataSourceList(single: Single<List<KegEntity>>) {
        whenever(kegRemoteDataSource.list())
                .thenReturn(single)
    }

    private fun stubKegDataSourceFactoryCreateRemote() {
        whenever(kegDataSourceFactory.createRemote())
                .thenReturn(kegRemoteDataSource)
    }

    private fun stubKegMapperMapFromEntity(kegEntity: KegEntity, keg: Keg) {
        whenever(kegMapper.mapFromEntity(kegEntity))
                .thenReturn(keg)
    }
    //</editor-fold>

}