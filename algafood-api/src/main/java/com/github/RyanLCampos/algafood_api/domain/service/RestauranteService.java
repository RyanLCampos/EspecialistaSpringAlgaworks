package com.github.RyanLCampos.algafood_api.domain.service;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Cozinha;
import com.github.RyanLCampos.algafood_api.domain.model.Restaurante;
import com.github.RyanLCampos.algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    public List<Restaurante> obterTodos(){
        return restauranteRepository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante){

        Long cozinhaId = restaurante.getCozinha().getId();

        existeCozinha(cozinhaId);

        return restauranteRepository.save(restaurante);
    }

    public Optional<Restaurante> obterPorId(Long id){
        return restauranteRepository.findById(id);
    }

    public void deletar(Restaurante restaurante){
        restauranteRepository.delete(restaurante);
    }

    private void existeCozinha(Long cozinhaId){
        Cozinha cozinha = cozinhaService.obterPorId(cozinhaId);

        if(cozinha == null) {
            throw new EntidadeNaoEncontradoException(
                    String.format("Não existe cozinha cadastrada com código: %d", cozinhaId)
            );
        }

    }

}
