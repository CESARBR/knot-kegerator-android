package br.org.cesar.knot.kegerator.domain.test

import br.org.cesar.knot.kegerator.domain.interactor.GetBeers
import br.org.cesar.knot.kegerator.domain.model.Beer
import br.org.cesar.knot.kegerator.domain.repository.BeerRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single

import org.junit.Before
import org.junit.Test

class GetBeersTest {

    private lateinit var getBeerList: GetBeers
    private lateinit var mockBeerRepository: BeerRepository

    @Before
    fun setup() {
        mockBeerRepository = mock()
        getBeerList = GetBeers(mockBeerRepository)
    }

    @Test
    fun `should call repository`() {
        getBeerList.execute()
        verify(mockBeerRepository).list()
    }

    @Test
    fun `should receive one onComplete event`() {
        stubBeerRepositoryList(Single.just(BeerFactory.makeBeerList(2)))
        val testObserver = getBeerList.execute().test()
        testObserver.assertComplete()
    }

    @Test
    fun `should return data`() {
        val beers = BeerFactory.makeBeerList(2)
        stubBeerRepositoryList(Single.just(beers))
        val testObserver = getBeerList.execute().test()
        testObserver.assertValue(beers)
    }

    private fun stubBeerRepositoryList(single: Single<List<Beer>>) {
        whenever(mockBeerRepository.list())
                .thenReturn(single)
    }
}
