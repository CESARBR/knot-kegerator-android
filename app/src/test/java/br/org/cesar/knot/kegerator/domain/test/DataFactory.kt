package br.org.cesar.knot.kegerator.domain.test

/**
 * Factory class for data instances
 */
class DataFactory {

    companion object Factory {

        fun randomUuid(): String {
            return java.util.UUID.randomUUID().toString()
        }

        fun randomInt(): Int {
            return Math.random().toInt()
        }

        fun randomString(): String {
            return ""
        }
    }
}
