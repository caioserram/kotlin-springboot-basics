package com.acme.tour.model

import org.intellij.lang.annotations.Identifier
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Promocao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,
    val descricao: String = "",
    val local: String = "",
    val isAllInclusive: Boolean = false,
    val qtdDias: Int = 0,
    val preco: Double = 0.0
)
