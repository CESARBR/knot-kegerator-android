package br.org.cesar.knot.kegerator.test

import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomInt
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomString
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity

class KegEntityFactory {

    companion object Factory {

        fun makeKegEntityList(count: Int): List<KegEntity> {
            val kegs = mutableListOf<KegEntity>()
            repeat(count) {
                kegs.add(makeKegEntity())
            }
            return kegs
        }

        fun makeKegEntity(): KegEntity {
            return KegEntity(randomString(), randomString(), randomInt())
        }

    }
}