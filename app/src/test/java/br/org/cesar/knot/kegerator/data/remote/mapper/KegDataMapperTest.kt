package br.org.cesar.knot.kegerator.data.remote.mapper

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.data.repository.model.KegData
import br.org.cesar.knot.kegerator.test.KegDataFactory
import br.org.cesar.knot.kegerator.test.KegRemoteFactory
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class KegDataMapperTest {

    private lateinit var kegDataMapper: KegDataMapper

    @Before
    fun setUp() {
        kegDataMapper = KegDataMapper()
    }

    @Test
    fun mapToRemote() {
        val kegData = KegDataFactory.makeKegData()
        val kegRemote = kegDataMapper.mapToRemote(kegData)

        assertKegDataEquality(kegRemote, kegData)
    }

    @Test
    fun mapFromRemote() {
        val kegRemote = KegRemoteFactory.makeKegRemote()
        val kegData = kegDataMapper.mapFromRemote(kegRemote)

        assertKegDataEquality(kegRemote, kegData)
    }

    private fun assertKegDataEquality(kegRemote: KegRemote, kegData: KegData) {
        assertEquals(kegRemote.id, kegData.id)
        assertEquals(kegRemote.name, kegData.name)
        assertEquals(kegRemote.weight, kegData.weight)
    }

}