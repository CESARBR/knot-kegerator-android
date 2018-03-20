package br.org.cesar.knot.kegerator.domain.test

import java.util.UUID

/**
 * Factory class for data instances
 */
class DataFactory {

    companion object Factory {

        fun randomUuid(): UUID {
            return java.util.UUID.randomUUID()
        }

        fun randomInt(): Int {
            return Math.random().toInt()
        }

        fun randomString(): String {
            return ""
        }
    }
}
