package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query("select * from promocao p where p.preco <= 9000", nativeQuery = true)
    fun getPrecoMenorQue9000(): List<Promocao>
}