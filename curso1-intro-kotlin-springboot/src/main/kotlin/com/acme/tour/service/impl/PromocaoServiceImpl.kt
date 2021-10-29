package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.repository.PromocaoRepository
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

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

    override fun searchByLocal(local: String): List<Promocao> = listOf()

    // Paginação nunca foi tão fácil!!!
    override fun getAll(start: Int, size: Int): List<Promocao> {
        val pageable: PageRequest = PageRequest.of(start, size, Sort.by("local"))
        return this.promocaoRepository.findAll(pageable).toList()
    }

    // Ordenação nunca foi tão fácil!!!
    override fun getAllSortByLocal(): List<Promocao> {
        val sort: Sort = Sort.by("local")
        return this.promocaoRepository.findAll(sort).toList()
    }

    override fun count(): Long = this.promocaoRepository.count()

    override fun findAllPrecoMenorQue9000(): List<Promocao> = this.promocaoRepository.getPrecoMenorQue9000()
}