package com.acme.tour.controller

import com.acme.tour.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class PromocaoController {

    @Autowired
    lateinit var promocoes: ConcurrentHashMap<Long, Promocao>

    @RequestMapping(value=["/sayHello"], method = [RequestMethod.GET])
    fun sayHello(): String {
        return "Hello World"
    }

    @RequestMapping(value = ["/promocoes"], method = [RequestMethod.GET])
    fun getAll() = this.promocoes.entries

    @RequestMapping(value = ["/promocoes/{id}"], method = [RequestMethod.GET])
    fun getById(@PathVariable id: Long) = this.promocoes[id]

    @RequestMapping(value = ["/promocoes"], method = [RequestMethod.POST])
    fun create(@RequestBody promocao: Promocao) {
        this.promocoes[promocao.id] = promocao
    }

    @RequestMapping(value=["/promocoes/{id}"], method = [RequestMethod.DELETE])
    fun delete(@PathVariable id: Long) {
        this.promocoes.remove(id)
    }

    @RequestMapping(value=["/promocoes/{id}"], method = [RequestMethod.PUT])
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) {
        this.promocoes.remove(id)
        this.promocoes[id] = promocao
    }
}