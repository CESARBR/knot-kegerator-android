package br.org.cesar.knot.kegerator.domain.model

import java.util.UUID

/**
 * The model class to store Keg data in.
 *
 * @param id the Beer ID
 * @param name the beer name
 * @param brand the beer brand
 * @param style the beer style
 */

data class Beer constructor(val id: UUID, val name: String, val brand: String, val style: String)
