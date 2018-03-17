package br.org.cesar.knot.kegerator.test

import java.util.*

/**
 * Factory class for data instances
 */
class DataFactory {

    companion object Factory {

        fun randomUuid(): UUID {
            return UUID.randomUUID()
        }

        fun randomString(): String {
            return UUID.randomUUID().toString()
        }

        fun randomInt(): Int {
            return Math.random().toInt()
        }

    }

}