package com.acme.tour.controller

import com.acme.tour.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value=["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocoes: ConcurrentHashMap<Long, Promocao>

    @GetMapping()
    fun getAll(@RequestParam(required = false ,defaultValue = "") local: String) =
        this.promocoes
            .filter { it.value.local.contains(local,true) }
            .map(Map.Entry<Long,Promocao>::value).toList() // retorna apenas o valor contigo no map para ser convertido em lista

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = this.promocoes[id]

    @PostMapping()
    fun create(@RequestBody promocao: Promocao) {
        this.promocoes[promocao.id] = promocao
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        this.promocoes.remove(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) {
        this.promocoes.remove(id)
        this.promocoes[id] = promocao
    }
}