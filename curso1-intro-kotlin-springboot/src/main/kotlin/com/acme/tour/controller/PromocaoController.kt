package com.acme.tour.controller

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value=["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping()
    fun getAll(@RequestParam(required = false ,defaultValue = "0") start: Int,
               @RequestParam(required = false ,defaultValue = "3") size: Int): ResponseEntity<List<Promocao>> {
        var status = HttpStatus.OK
        val listaPromocoes = this.promocaoService.getAll(start,size)

        if (listaPromocoes.isEmpty()){
            status = HttpStatus.NOT_FOUND
        }

        return ResponseEntity(listaPromocoes, status)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Promocao?> {
        val promocao = this.promocaoService.getById(id)
        val status = if (promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(promocao,status)
    }

    // Unit é o objeto vazio
    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<Unit>  {
        this.promocaoService.create(promocao)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            this.promocaoService.delete(id)
            status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null){
            this.promocaoService.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit,status)
    }

    @GetMapping("/count")
    fun count() = ResponseEntity.ok().body(mapOf("count" to this.promocaoService.count()))

    @GetMapping("/ordenado")
    fun getAllSortedByLocal() = this.promocaoService.getAllSortByLocal()

    @GetMapping("/precoMenorQue9000")
    fun getPromocaoMenorQue9000() = this.promocaoService.findAllPrecoMenorQue9000()
}