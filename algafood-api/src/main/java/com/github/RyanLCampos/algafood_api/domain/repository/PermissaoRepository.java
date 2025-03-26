package com.github.RyanLCampos.algafood_api.domain.repository;

import com.github.RyanLCampos.algafood_api.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> todos();

    Permissao porId(Long id);

    Permissao adicionar(Permissao permissao);

    void remover(Permissao permissao);
}
