package com.github.RyanLCampos.algafood_api.domain.repository;

import com.github.RyanLCampos.algafood_api.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> todos();

    Cidade porId(Long id);

    Cidade adicionar(Cidade cidade);

    void remover(Cidade cidade);

}
