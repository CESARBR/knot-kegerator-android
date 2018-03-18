package br.org.cesar.knot.kegerator.injection

import br.org.cesar.knot.kegerator.data.KegGateway
import br.org.cesar.knot.kegerator.data.remote.KegeratorServiceFactory
import br.org.cesar.knot.kegerator.data.remote.RestApi
import br.org.cesar.knot.kegerator.data.remote.mapper.KegEntityMapper
import br.org.cesar.knot.kegerator.data.repository.KegDataSourceFactory
import br.org.cesar.knot.kegerator.data.repository.datasource.KegApi
import br.org.cesar.knot.kegerator.data.repository.datasource.KegRemoteDataSource
import br.org.cesar.knot.kegerator.data.repository.mapper.KegMapper
import br.org.cesar.knot.kegerator.domain.interactor.GetKegs
import br.org.cesar.knot.kegerator.domain.interactor.UseCase
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import br.org.cesar.knot.kegerator.executor.*
import br.org.cesar.knot.kegerator.presentation.keg.KegsContract
import br.org.cesar.knot.kegerator.presentation.keg.KegsPresenter
import br.org.cesar.knot.kegerator.presentation.mapper.KegModelMapper

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
                KegDataSourceFactory(KegRemoteDataSource(provideKegApi())),
                KegMapper()
        )
    }

    fun provideKegApi(): KegApi {
        return RestApi(
                KegeratorServiceFactory.makeKegeratorService("", true),
                KegEntityMapper()
        )
    }

    fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    fun providePostExecutionThread(): PostExecutionThread {
        return MainThread()
    }
}