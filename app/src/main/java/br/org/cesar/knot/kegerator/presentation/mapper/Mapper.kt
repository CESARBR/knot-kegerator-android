package br.org.cesar.knot.kegerator.presentation.mapper

interface Mapper<E, D> {

    fun mapFromModel(type: E): D

    fun mapToModel(type: D): E

}