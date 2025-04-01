package com.github.RyanLCampos.algafood_api.domain.repository;

import com.github.RyanLCampos.algafood_api.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
