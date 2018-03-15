package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.executor.PostExecutionThread
import br.org.cesar.knot.kegerator.domain.executor.ThreadExecutor
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import br.org.cesar.knot.kegerator.domain.test.KegFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetKegListTest {

    private lateinit var getKegList: GetKegList

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockKegRepository: KegRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockKegRepository = mock()
        getKegList = GetKegList(mockThreadExecutor, mockPostExecutionThread, mockKegRepository)
    }

    @Test
    fun `should call repository`() {
        getKegList.buildUseCaseObservable()
        verify(mockKegRepository).list()
    }

    @Test
    fun `should receive one onComplete event`() {
        stubKegRepositoryList(Single.just(KegFactory.makeKegList(2)))
        val testObserver = getKegList.buildUseCaseObservable().test()
        testObserver.assertComplete()

    }

    @Test
    fun `should return data`() {
        val kegs = KegFactory.makeKegList(2)
        stubKegRepositoryList(Single.just(kegs))
        val testObserver = getKegList.buildUseCaseObservable().test()
        testObserver.assertValue(kegs)
    }

    private fun stubKegRepositoryList(single: Single<List<Keg>>) {
        whenever(mockKegRepository.list())
                .thenReturn(single)
    }
}