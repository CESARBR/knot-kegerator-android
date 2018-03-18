package br.org.cesar.knot.kegerator.presentation.mapper

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.presentation.model.KegModel
import br.org.cesar.knot.kegerator.test.KegFactory
import br.org.cesar.knot.kegerator.test.KegModelFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class KegModelMapperTest {

    private lateinit var kegModelMapper: KegModelMapper

    @Before
    fun setUp() {
        kegModelMapper = KegModelMapper()
    }

    @Test
    fun mapToModel() {
        val keg = KegFactory.makeKeg()
        val kegModel = kegModelMapper.mapToModel(keg)

        assertKegDataEquality(kegModel, keg)
    }

    @Test
    fun mapFromModel() {
        val kegModel = KegModelFactory.makeKegModel()
        val keg = kegModelMapper.mapFromModel(kegModel)

        assertKegDataEquality(kegModel, keg)
    }


    private fun assertKegDataEquality(kegModel: KegModel, keg: Keg) {
        assertEquals(kegModel.id, keg.id.toString())
        assertEquals(kegModel.name, keg.name)
        assertEquals(kegModel.weight, keg.weight.toString())
    }
}