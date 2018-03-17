package br.org.cesar.knot.kegerator.domain.model

import java.util.*

/**
 * The model class to store Keg data in.
 *
 * @param id the keg ID
 * @param name the keg name
 * @param weight the keg weight
 */
data class Keg constructor(val id: UUID, val name: String, val weight: Int)