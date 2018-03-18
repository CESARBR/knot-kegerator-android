package br.org.cesar.knot.kegerator.presentation

interface BaseView<in T : BasePresenter> {

    fun setPresenter(presenter: T)

}