package com.acme.tour.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class Promocao {

    @RequestMapping(value=["/promocoes"], method = [RequestMethod.GET])
    fun sayHello(): String {
        return "Hello World"
    }
}