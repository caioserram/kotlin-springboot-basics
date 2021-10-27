package com.acme.tour.controller

import com.acme.tour.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class PromocaoController {

    @Autowired
    lateinit var initialPromocoes: ConcurrentHashMap<Long, Promocao>

    @RequestMapping(value=["/sayHello"], method = [RequestMethod.GET])
    fun sayHello(): String {
        return "Hello World"
    }

    @RequestMapping(value = ["/promocoes"], method = [RequestMethod.GET])
    fun getPromocao() = this.initialPromocoes[2]
}