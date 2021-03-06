package br.org.cesar.knot.kegerator.injection

import br.org.cesar.knot.kegerator.data.KegGateway
import br.org.cesar.knot.kegerator.data.repository.datasource.KegApi
import br.org.cesar.knot.kegerator.data.repository.datasource.KegRemoteDataSource
import br.org.cesar.knot.kegerator.data.repository.mapper.KegMapper
import br.org.cesar.knot.kegerator.data.repository.model.KegData
import br.org.cesar.knot.kegerator.domain.interactor.GetKegs
import br.org.cesar.knot.kegerator.domain.interactor.UseCase
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import br.org.cesar.knot.kegerator.executor.*
import br.org.cesar.knot.kegerator.presentation.keg.KegsContract
import br.org.cesar.knot.kegerator.presentation.keg.KegsPresenter
import br.org.cesar.knot.kegerator.presentation.mapper.KegModelMapper
import io.reactivex.Single
import java.util.concurrent.TimeUnit

object Injection {

    fun provideKegsPresenter(kegsViewModel: KegsContract.ViewModel): KegsPresenter {
        return KegsPresenter(
                kegsViewModel,
                provideGetKegsUseCase(),
                KegModelMapper()
        )
    }

    fun provideGetKegsUseCase(): ObservableUseCase<List<Keg>, Void?> {
        return ObservableUseCase(
                provideGetKegs(),
                provideThreadExecutor(),
                providePostExecutionThread()
        )
    }

    fun provideGetKegs(): UseCase<List<Keg>, Void?> {
        return GetKegs(provideKegRepository())
    }

    fun provideKegRepository(): KegRepository {
        return KegGateway(
                KegRemoteDataSource(provideKegApi()),
                KegMapper()
        )
    }

    fun provideKegApi(): KegApi {
        return OffLineKegApi()
    }

    class OffLineKegApi(): KegApi {
        override fun fetchKegs(): Single<List<KegData>> {
            val single = Single.just(
                listOf<KegData>(
                    KegData("d6600558-f101-45be-bf8a-4b5aed40cf9f", "Stainless steel", 10),
                    KegData("58a6168c-5676-41a0-8beb-b983764eb797", "Rubber", 5)
                )
            )

            return single.delay(5, TimeUnit.SECONDS)
        }
    }

    fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    fun providePostExecutionThread(): PostExecutionThread {
        return MainThread()
    }
}