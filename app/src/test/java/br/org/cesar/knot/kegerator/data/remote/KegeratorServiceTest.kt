package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class KegeratorServiceTest {

    private lateinit var kegeratorService: KegeratorService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        kegeratorService = KegeratorServiceFactory
                .makeKegeratorService(mockWebServer.url("").toString(), true)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch kegs returns data`() {
        mockWebServer.enqueue(createValidFetchKegsResponse())

        val testObserver = kegeratorService.fetchKegs().test()
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertValue(createValidKegRemoteList())
    }

    @Test
    fun `fetch kegs call onError when unauthorized`() {
        mockWebServer.enqueue(MockResponse().setResponseCode(401))

        val testObserver = kegeratorService.fetchKegs().test()
        testObserver.awaitTerminalEvent()
        testObserver.assertError(HttpException::class.java)
    }

    private fun createValidFetchKegsResponse(): MockResponse {
        return MockResponse().setBody(
                "[\n" +
                "  {\n" +
                "    \"id\": \"d6600558-f101-45be-bf8a-4b5aed40cf9f\",\n" +
                "    \"name\": \"Stainless steel\",\n" +
                "    \"weight\": 10\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"58a6168c-5676-41a0-8beb-b983764eb797\",\n" +
                "    \"name\": \"Rubber\",\n" +
                "    \"weight\": 5\n" +
                "  }\n" +
                "]"
        )
    }

    private fun createValidKegRemoteList(): List<KegRemote> {
        return listOf<KegRemote>(
            KegRemote("d6600558-f101-45be-bf8a-4b5aed40cf9f", "Stainless steel", 10),
            KegRemote("58a6168c-5676-41a0-8beb-b983764eb797", "Rubber", 5)
        )
    }
}