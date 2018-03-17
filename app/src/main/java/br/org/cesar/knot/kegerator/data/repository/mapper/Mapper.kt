package br.org.cesar.knot.kegerator.data.repository.mapper

interface Mapper<E, D> {

    fun mapFromData(type: E): D

    fun mapToData(type: D): E

}