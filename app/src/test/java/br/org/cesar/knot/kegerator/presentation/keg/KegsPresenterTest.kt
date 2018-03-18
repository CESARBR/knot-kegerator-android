package br.org.cesar.knot.kegerator.presentation.keg

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.executor.ObservableUseCase
import br.org.cesar.knot.kegerator.presentation.mapper.KegModelMapper
import br.org.cesar.knot.kegerator.test.KegFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test

class KegsPresenterTest {

    lateinit var kegsPresenter: KegsPresenter

    lateinit var mockKegsContractViewModel: KegsContract.ViewModel
    lateinit var mockGetKegsUseCase: ObservableUseCase<List<Keg>, Void?>
    lateinit var mockKegModelMapper: KegModelMapper

    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Keg>>>

    @Before
    fun setUp() {
        captor = argumentCaptor<DisposableSingleObserver<List<Keg>>>()
        mockKegsContractViewModel = mock()
        mockGetKegsUseCase = mock()
        mockKegModelMapper = mock()

        kegsPresenter = KegsPresenter(mockKegsContractViewModel,
                mockGetKegsUseCase,mockKegModelMapper)
    }

    @Test
    fun `retrieving kegs should change to loading state`() {
        kegsPresenter.retrieveKegs()

        verify(mockGetKegsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(KegFactory.makeKegList(2))
        verify(mockKegsContractViewModel).changeToLoadingState()
    }

    @Test
    fun `retrieving empty kegs list should change to empty state`() {
        kegsPresenter.retrieveKegs()

        verify(mockGetKegsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(KegFactory.makeKegList(0))
        verify(mockKegsContractViewModel).changeToEmptyState()
    }

    @Test
    fun `retrieving kegs should update kegs`() {
        val kegs = KegFactory.makeKegList(2)

        kegsPresenter.retrieveKegs()

        verify(mockGetKegsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(kegs)
        verify(mockKegsContractViewModel).updateKegs(
                kegs.map { mockKegModelMapper.mapToModel(it) }
        )
    }

    @Test
    fun `retrieving kegs with error should change to error state`() {
        kegsPresenter.retrieveKegs()

        verify(mockGetKegsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockKegsContractViewModel).changeToErrorState("")
    }
}