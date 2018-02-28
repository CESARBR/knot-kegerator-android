/**
 * BSD 3-Clause License
 *
 * Copyright (c) 2018, CESAR
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package br.org.cesar.knot.kegerator.ui.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Provider

abstract class MvpActivity<VIEW : MvpView, PRESENTER : MvpPresenter<VIEW>> : AppCompatActivity() {
    @Inject
    lateinit var viewProvider: Provider<VIEW>
    private lateinit var view: VIEW

    @Inject
    lateinit var presenterProvider: Provider<PRESENTER>
    private lateinit var presenter: PRESENTER

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        createPresenter()
        createView()
        attachView()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        detachView()
    }

    private fun createPresenter() {
        presenter = presenterProvider.get()
    }

    private fun createView() {
        view = viewProvider.get()
    }

    private fun attachView() {
        presenter.attachView(view)
    }

    private fun detachView() {
        presenter.detachView()
    }
}