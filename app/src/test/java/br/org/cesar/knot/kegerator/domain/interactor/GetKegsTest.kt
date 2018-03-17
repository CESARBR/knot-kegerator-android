package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import br.org.cesar.knot.kegerator.test.KegFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetKegsTest {

    private lateinit var getKegList: GetKegs
    private lateinit var mockKegRepository: KegRepository

    @Before
    fun setUp() {
        mockKegRepository = mock()
        getKegList = GetKegs(mockKegRepository)
    }

    @Test
    fun `should call repository`() {
        getKegList.execute()
        verify(mockKegRepository).list()
    }

    @Test
    fun `should receive one onComplete event`() {
        stubKegRepositoryList(Single.just(KegFactory.makeKegList(2)))
        val testObserver = getKegList.execute().test()
        testObserver.assertComplete()

    }

    @Test
    fun `should return data`() {
        val kegs = KegFactory.makeKegList(2)
        stubKegRepositoryList(Single.just(kegs))
        val testObserver = getKegList.execute().test()
        testObserver.assertValue(kegs)
    }

    private fun stubKegRepositoryList(single: Single<List<Keg>>) {
        whenever(mockKegRepository.list())
                .thenReturn(single)
    }
}