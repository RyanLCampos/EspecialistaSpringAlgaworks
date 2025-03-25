package com.github.RyanLCampos.algafood_api.domain.repository;

import com.github.RyanLCampos.algafood_api.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> todos();

    Cozinha porId(Long id);

    Cozinha adicionar(Cozinha cozinha);

    void remover(Cozinha cozinha);

}
