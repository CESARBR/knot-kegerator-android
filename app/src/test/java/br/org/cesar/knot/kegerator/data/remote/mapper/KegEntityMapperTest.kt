package br.org.cesar.knot.kegerator.data.remote.mapper

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.data.repository.mapper.KegMapper
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.test.KegEntityFactory
import br.org.cesar.knot.kegerator.test.KegFactory
import br.org.cesar.knot.kegerator.test.KegRemoteFactory
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class KegEntityMapperTest {

    private lateinit var kegEntityMapper: KegEntityMapper

    @Before
    fun setUp() {
        kegEntityMapper = KegEntityMapper()
    }

    @Test
    fun mapToRemote() {
        val kegEntity = KegEntityFactory.makeKegEntity()
        val kegRemote = kegEntityMapper.mapToRemote(kegEntity)

        assertKegDataEquality(kegRemote, kegEntity)
    }

    @Test
    fun mapFromRemote() {
        val kegRemote = KegRemoteFactory.makeKegRemote()
        val kegEntity = kegEntityMapper.mapFromRemote(kegRemote)

        assertKegDataEquality(kegRemote, kegEntity)
    }

    private fun assertKegDataEquality(kegRemote: KegRemote, kegEntity: KegEntity) {
        assertEquals(kegRemote.id, kegEntity.id)
        assertEquals(kegRemote.name, kegEntity.name)
        assertEquals(kegRemote.weight, kegEntity.weight)
    }

}