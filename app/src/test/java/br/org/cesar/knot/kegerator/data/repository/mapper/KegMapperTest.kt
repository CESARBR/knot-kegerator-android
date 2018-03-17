package br.org.cesar.knot.kegerator.data.repository.mapper

import br.org.cesar.knot.kegerator.test.KegEntityFactory
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.test.KegFactory
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class KegMapperTest {

    private lateinit var kegMapper: KegMapper

    @Before
    fun setUp() {
        kegMapper = KegMapper()
    }

    @Test
    fun mapToEntity() {
        val keg = KegFactory.makeKeg()
        val kegEntity = kegMapper.mapToEntity(keg)

        assertKegDataEquality(kegEntity, keg)
    }

    @Test
    fun mapFromEntity() {
        val kegEntity = KegEntityFactory.makeKegEntity()
        val keg = kegMapper.mapFromEntity(kegEntity)

        assertKegDataEquality(kegEntity, keg)
    }

    private fun assertKegDataEquality(kegEntity: KegEntity, keg: Keg) {
        assertEquals(kegEntity.id, keg.id.toString())
        assertEquals(kegEntity.name, keg.name)
        assertEquals(kegEntity.weight, keg.weight)
    }

}