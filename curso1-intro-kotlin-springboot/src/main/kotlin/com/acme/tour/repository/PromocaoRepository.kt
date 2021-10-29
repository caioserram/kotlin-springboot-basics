package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query("select p from Promocao p where p.preco <= :preco")
    fun getPrecoMenorQue(@Param("preco") preco: Double): List<Promocao>

    @Query("select p from Promocao p where p.local in :localNames")
    fun findByLocalInList(@Param("localNames") localNames: String)
}