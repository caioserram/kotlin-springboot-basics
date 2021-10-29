package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query("select p from Promocao p where p.preco <= :preco")
    fun getPrecoMenorQue(@Param("preco") preco: Double): List<Promocao>

    @Query("select p from Promocao p where p.local in :localNames")
    fun findByLocalInList(@Param("localNames") localNames: String)

    @Query("update Promocao p set p.preco = :valor where p.local = :local")
    @Transactional @Modifying
    fun updatePrecoByLocal(@Param("valor") valor: Double, @Param("local") local: String)
}