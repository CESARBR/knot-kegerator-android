package br.org.cesar.knot.kegerator.data.remote.mapper

interface Mapper<E, D> {

    fun mapFromRemote(type: E): D

    fun mapToRemote(type: D): E

}