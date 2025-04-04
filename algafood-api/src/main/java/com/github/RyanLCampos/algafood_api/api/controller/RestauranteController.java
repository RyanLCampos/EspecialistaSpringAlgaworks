package com.github.RyanLCampos.algafood_api.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Restaurante;
import com.github.RyanLCampos.algafood_api.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante){
        try{
            Restaurante restauranteCadastrado = restauranteService.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastrado);
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar(){
        List<Restaurante> restaurantes = restauranteService.obterTodos();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable("id") Long id){
        try{
            Optional<Restaurante> restaurante = restauranteService.obterPorId(id);

            if(restaurante.isEmpty()){
                throw new EntidadeNaoEncontradoException("Usuário não encontrado");
            }

            return ResponseEntity.ok(restaurante.get());
        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable("id") Long id, @RequestBody Restaurante restaurante){
        try{
            Optional<Restaurante> optionalRestaurante = restauranteService.obterPorId(id);

            if(optionalRestaurante.isPresent()){
                Restaurante restauranteAtual = optionalRestaurante.get();

                BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

                restauranteAtual = restauranteService.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }

            return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradoException | DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable("id") Long id,
                                              @RequestBody Map<String, Object> campos){
        Optional<Restaurante> restauranteOptional = restauranteService.obterPorId(id);

        if(restauranteOptional.isPresent()){

            Restaurante restauranteAtual = restauranteOptional.get();

            merge(campos, restauranteAtual);

            return atualizar(id, restauranteAtual);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id){
        try {
            Optional<Restaurante> optionalRestaurante = restauranteService.obterPorId(id);

            if(optionalRestaurante.isEmpty()){
                throw new EntidadeNaoEncontradoException(
                        String.format("Não existe restaurante cadastrado com código: %d", id)
                );
            }

            restauranteService.deletar(optionalRestaurante.get());

            return ResponseEntity.noContent().build();

        }catch (EntidadeNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();

        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade); // Procura a propriedade dentro da entidade
            assert field != null;
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem); // Busca o valor dentro do restauranteOrigem

            ReflectionUtils.setField(field, restauranteDestino, novoValor); // Atribui o valor ao restaurante
        });

    }
}
