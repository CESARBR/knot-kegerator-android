package br.org.cesar.knot.kegerator.data.repository.mapper

import br.org.cesar.knot.kegerator.test.KegDataFactory
import br.org.cesar.knot.kegerator.data.repository.model.KegData
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
        val kegEntity = kegMapper.mapToData(keg)

        assertKegDataEquality(kegEntity, keg)
    }

    @Test
    fun mapFromEntity() {
        val kegEntity = KegDataFactory.makeKegData()
        val keg = kegMapper.mapFromData(kegEntity)

        assertKegDataEquality(kegEntity, keg)
    }

    private fun assertKegDataEquality(kegData: KegData, keg: Keg) {
        assertEquals(kegData.id, keg.id.toString())
        assertEquals(kegData.name, keg.name)
        assertEquals(kegData.weight, keg.weight)
    }

}