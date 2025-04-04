package com.github.RyanLCampos.algafood_api.api.controller;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Cidade;
import com.github.RyanLCampos.algafood_api.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade){
        try{
            Cidade cidadeCadastrado = service.salvar(cidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeCadastrado);
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Cidade>> listar(){
        List<Cidade> cidades = service.obterTodos();

        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable("id") Long id){
        try{
            Optional<Cidade> cidadeOptional = service.obterPorId(id);

            if(cidadeOptional.isEmpty()){
                throw new EntidadeNaoEncontradoException("Usuário não encontrado");
            }

            return ResponseEntity.ok(cidadeOptional.get());
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable("id") Long id, @RequestBody Cidade cidade){
        try {
            Optional<Cidade> optionalCidade = service.obterPorId(id);

            if(optionalCidade.isPresent()){

                Cidade cidadeAtual = optionalCidade.get();

                BeanUtils.copyProperties(cidade, cidadeAtual, "id");

                cidadeAtual = service.salvar(cidadeAtual);

                return ResponseEntity.ok(cidadeAtual);
            }

            return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id){
        try {
            Optional<Cidade> optionalCidade = service.obterPorId(id);

            if(optionalCidade.isEmpty()){
                throw new EntidadeNaoEncontradoException(
                        String.format("Não existe cidade cadastrada com código: %d", id)
                );
            }

            service.deletar(optionalCidade.get());

            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }
}
