package br.org.cesar.knot.kegerator.domain.interactor


import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import org.junit.Test
import java.util.*

class GetKegListTest {

    @Test
    fun `should call keg repository on execute`() {

        var getKegListCalled: Boolean = false

        val getKegList: GetKegList = GetKegList(object: KegRepository{
            override fun getKegList(): List<Keg> {
                getKegListCalled = true
                return Collections.emptyList()
            }
        })

        getKegList.execute()

        assert(getKegListCalled)
    }
}