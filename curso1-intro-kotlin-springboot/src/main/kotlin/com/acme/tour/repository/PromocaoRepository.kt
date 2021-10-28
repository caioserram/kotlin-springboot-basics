package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PromocaoRepository: CrudRepository<Promocao, Long>