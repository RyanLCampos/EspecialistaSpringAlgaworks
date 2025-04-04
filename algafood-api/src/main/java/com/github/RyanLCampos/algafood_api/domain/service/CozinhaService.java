package com.github.RyanLCampos.algafood_api.domain.service;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeEmUsoException;
import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Cozinha;
import com.github.RyanLCampos.algafood_api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Cozinha obterPorId(Long id){
        Optional<Cozinha> optionalCozinha = cozinhaRepository.findById(id);

        if(optionalCozinha.isEmpty()){
            throw new EntidadeNaoEncontradoException("Não existe cozinha cadastrada.");
        }

        return optionalCozinha.get();
    }

    public void remover(Long id){
        try {
            Cozinha cozinha = obterPorId(id);

            cozinhaRepository.delete(cozinha);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("A cozinha de ID " + id + " não pode ser removida, pois está associada a um ou mais restaurantes.");
        }
    }

}
