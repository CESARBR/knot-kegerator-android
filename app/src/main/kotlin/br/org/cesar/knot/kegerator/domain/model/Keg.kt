package br.org.cesar.knot.kegerator.domain.model

import java.util.*

data class Keg constructor(val id: UUID, val name: String, val weight: Int){}