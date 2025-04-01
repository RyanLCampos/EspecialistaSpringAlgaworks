package com.github.RyanLCampos.algafood_api.domain.repository;

import com.github.RyanLCampos.algafood_api.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
