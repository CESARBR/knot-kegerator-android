package br.org.cesar.knot.kegerator.domain.test

import br.org.cesar.knot.kegerator.domain.model.Beer
import br.org.cesar.knot.kegerator.domain.test.DataFactory.Factory.randomString
import java.util.UUID

class BeerFactory {

    companion object Factory {

        fun makeBeerList(count: Int): List<Beer> {
            val beers = mutableListOf<Beer>()
            repeat(count) {
                beers.add(makeBeer())
            }
            return beers
        }

        fun makeBeer(): Beer {
            return Beer(UUID.randomUUID(), randomString(), randomString(), randomString())
        }

    }
}
