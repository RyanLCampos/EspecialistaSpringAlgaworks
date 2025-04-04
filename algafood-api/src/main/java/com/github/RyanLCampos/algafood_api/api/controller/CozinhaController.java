package com.github.RyanLCampos.algafood_api.api.controller;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeEmUsoException;
import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Cozinha;
import com.github.RyanLCampos.algafood_api.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha){

        Cozinha cozinhaCadastrada = cozinhaService.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaCadastrada);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cozinha> listar(){
        return cozinhaService.todos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("id") Long id){
        try{
            Cozinha cozinha = cozinhaService.obterPorId(id);

            return ResponseEntity.ok(cozinha);
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable("id") Long id, @RequestBody Cozinha cozinha){
        try{
            Cozinha cozinhaAtual = cozinhaService.obterPorId(id);

            BeanUtils.copyProperties(cozinha, cozinhaAtual ,"id");

            cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

            return ResponseEntity.ok(cozinhaAtual);
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") Long id){
        try{
            cozinhaService.remover(id);

            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
