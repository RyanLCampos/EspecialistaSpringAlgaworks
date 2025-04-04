package com.github.RyanLCampos.algafood_api.api.controller;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeEmUsoException;
import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Estado;
import com.github.RyanLCampos.algafood_api.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
        estado = estadoService.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable("id") Long id, @RequestBody Estado estado){
        try{
            Estado estadoAtual = estadoService.porId(id);

            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual  = estadoService.salvar(estadoAtual);

            return ResponseEntity.ok(estadoAtual);
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable("id") Long id){
        try {

            Estado estado = estadoService.porId(id);

            return ResponseEntity.ok(estado);
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listar(){
        List<Estado> estados = estadoService.obterTodos();
        return ResponseEntity.ok(estados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") Long id){
        try{

            estadoService.deletar(id);

            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
