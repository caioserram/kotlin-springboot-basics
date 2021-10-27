package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl:PromocaoService {

    companion object {
        val initialPromocoes = arrayOf(
            Promocao(1, "Maravilhosa viagem a Cancun", "Cancun", true, 7, 4999.99),
            Promocao(2,"Viagem radical com rapel e escalada", "Nova Zelandia", false, 12, 12000.0),
            Promocao(3, "Viagem espiritual", "Thailandia", false, 17, 15000.0),
            Promocao(4, "Viagem com a familia", "Gramado", false, 5, 3500.33)
        )
    }

    var promocoes = ConcurrentHashMap<Long, Promocao> ( initialPromocoes.associateBy ( Promocao::id )  )

    override fun create(promocao: Promocao) {
        this.promocoes[promocao.id] = promocao
    }

    override fun getById(id: Long): Promocao? {
        return this.promocoes[id]
    }

    override fun update(id: Long, promocao: Promocao) {
        this.delete(id)
        this.create(promocao)
    }

    override fun delete(id: Long) {
        this.promocoes.remove(id)
    }

    override fun searchByLocal(local: String): List<Promocao> =
        this.promocoes
            .filter { it.value.local.contains(local,true) }
            .map(Map.Entry<Long,Promocao>::value).toList() // retorna apenas o valor contigo no map para ser convertido em lista
}