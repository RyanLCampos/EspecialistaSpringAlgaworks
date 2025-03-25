package com.github.RyanLCampos.algafood_api.domain.repository;

import com.github.RyanLCampos.algafood_api.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> todos();

    Restaurante porId(Long id);

    Restaurante adicionar(Restaurante restaurante);

    void remover(Restaurante restaurante);

}
