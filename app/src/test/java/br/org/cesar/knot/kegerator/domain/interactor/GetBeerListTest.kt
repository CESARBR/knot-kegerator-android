package br.org.cesar.knot.kegerator.domain.test

import br.org.cesar.knot.kegerator.domain.executor.PostExecutionThread
import br.org.cesar.knot.kegerator.domain.executor.ThreadExecutor
import br.org.cesar.knot.kegerator.domain.interactor.GetBeerList
import br.org.cesar.knot.kegerator.domain.model.Beer
import br.org.cesar.knot.kegerator.domain.repository.BeerRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single

import org.junit.Before
import org.junit.Test

class GetBeerListTest {

    private lateinit var getBeerList: GetBeerList
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockBeerRepository: BeerRepository

    @Before
    fun setup() {

        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockBeerRepository = mock()
        getBeerList = GetBeerList(mockThreadExecutor, mockPostExecutionThread, mockBeerRepository)
    }

    @Test
    fun `should call repository`() {
        getBeerList.buildUseCaseObservable()
        verify(mockBeerRepository).list()
    }

    @Test
    fun `should receive one onComplete event`() {
        stubBeerRepositoryList(Single.just(BeerFactory.makeBeerList(2)))
        val testObserver = getBeerList.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun `should return data`() {
        val beers = BeerFactory.makeBeerList(2)
        stubBeerRepositoryList(Single.just(beers))
        val testObserver = getBeerList.buildUseCaseObservable().test()
        testObserver.assertValue(beers)
    }

    private fun stubBeerRepositoryList(single: Single<List<Beer>>) {
        whenever(mockBeerRepository.list())
                .thenReturn(single)
    }
}
