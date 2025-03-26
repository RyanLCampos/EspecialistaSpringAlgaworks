package com.github.RyanLCampos.algafood_api.domain.repository;

import com.github.RyanLCampos.algafood_api.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> todos();

    Estado porId(Long id);

    Estado adicionar(Estado estado);

    void remover(Estado estado);
}
