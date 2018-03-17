package br.org.cesar.knot.kegerator.test

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomInt
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomString

class KegRemoteFactory {

    companion object Factory {

        fun makeKegRemoteList(count: Int): List<KegRemote> {
            val kegs = mutableListOf<KegRemote>()
            repeat(count) {
                kegs.add(makeKegRemote())
            }
            return kegs
        }

        fun makeKegRemote(): KegRemote {
            return KegRemote(randomString(), randomString(), randomInt())
        }

    }
}