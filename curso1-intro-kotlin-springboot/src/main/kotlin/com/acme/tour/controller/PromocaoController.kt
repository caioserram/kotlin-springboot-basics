package com.acme.tour.controller

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value=["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping()
    fun getAll(@RequestParam(required = false ,defaultValue = "") localFilter: String) =
        this.promocaoService.searchByLocal(localFilter)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Promocao?> {
        val promocao = this.promocaoService.getById(id)
        val status = if (promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(promocao,status)
    }

    @PostMapping()
    fun create(@RequestBody promocao: Promocao) {
        this.promocaoService.create(promocao)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        this.promocaoService.delete(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) {
        this.promocaoService.update(id, promocao)
    }
}