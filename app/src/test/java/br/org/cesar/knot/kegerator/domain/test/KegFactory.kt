package br.org.cesar.knot.kegerator.domain.test

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.test.DataFactory.Factory.randomInt
import br.org.cesar.knot.kegerator.domain.test.DataFactory.Factory.randomUuid
import java.util.*

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
            return Keg(UUID.randomUUID(), randomUuid(), randomInt())
        }

    }
}