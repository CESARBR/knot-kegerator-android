package br.org.cesar.knot.kegerator.test

import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomInt
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomString
import br.org.cesar.knot.kegerator.data.repository.model.KegData

class KegDataFactory {

    companion object Factory {

        fun makeKegDataList(count: Int): List<KegData> {
            val kegs = mutableListOf<KegData>()
            repeat(count) {
                kegs.add(makeKegData())
            }
            return kegs
        }

        fun makeKegData(): KegData {
            return KegData(randomString(), randomString(), randomInt())
        }

    }
}