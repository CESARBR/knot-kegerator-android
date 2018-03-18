package br.org.cesar.knot.kegerator.test

import br.org.cesar.knot.kegerator.presentation.model.KegModel
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomInt
import br.org.cesar.knot.kegerator.test.DataFactory.Factory.randomString

class KegModelFactory {

    companion object Factory {

        fun makeKegModelList(count: Int): List<KegModel> {
            val kegs = mutableListOf<KegModel>()
            repeat(count) {
                kegs.add(makeKegModel())
            }
            return kegs
        }

        fun makeKegModel(): KegModel {
            return KegModel(randomString(), randomString(), randomInt().toString())
        }

    }
}