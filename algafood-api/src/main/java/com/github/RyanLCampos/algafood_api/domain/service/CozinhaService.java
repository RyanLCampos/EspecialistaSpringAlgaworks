package com.github.RyanLCampos.algafood_api.domain.service;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Cozinha;
import com.github.RyanLCampos.algafood_api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public List<Cozinha> todos(){
        return cozinhaRepository.findAll();
    }

    public Cozinha porId(Long id){
        Optional<Cozinha> optionalCozinha = cozinhaRepository.findById(id);

        if(optionalCozinha.isEmpty()){
            throw new EntidadeNaoEncontradoException("Não existe cozinha cadastrada.");
        }

        return optionalCozinha.get();
    }

    public void remover(Long id){
        Cozinha cozinha = porId(id);

        cozinhaRepository.delete(cozinha);
    }

}
