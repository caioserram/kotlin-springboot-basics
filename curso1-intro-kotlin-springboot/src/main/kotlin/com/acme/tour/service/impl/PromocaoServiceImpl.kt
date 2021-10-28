package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.repository.PromocaoRepository
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl(
    promocaoRepository: PromocaoRepository
):PromocaoService {

    @Autowired
    lateinit var promocaoRepository: PromocaoRepository

    override fun create(promocao: Promocao) {
        this.promocaoRepository.save(promocao)
    }

    override fun getById(id: Long): Promocao? = this.promocaoRepository.findByIdOrNull(id)

    override fun update(id: Long, promocao: Promocao) {
        this.create(promocao)
    }

    override fun delete(id: Long) {
        this.promocaoRepository.deleteById(id)
    }

    override fun searchByLocal(local: String): List<Promocao> = this.getAll().filter { it.local.contains(local,true) }

    override fun getAll(): List<Promocao> = this.promocaoRepository.findAll().toList()

    override fun count(): Long = this.promocaoRepository.count()
}