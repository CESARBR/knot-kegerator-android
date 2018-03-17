package br.org.cesar.knot.kegerator.test

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomInt
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomString
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomUuid

class KegFactory {

    companion object Factory {

        fun makeKegList(count: Int): List<Keg> {
            val kegs = mutableListOf<Keg>()
            repeat(count) {
                kegs.add(makeKeg())
            }
            return kegs
        }

        fun makeKeg(): Keg {
            return Keg(randomUuid(), randomString(), randomInt())
        }

    }
}