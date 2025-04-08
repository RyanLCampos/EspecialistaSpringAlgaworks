package com.github.RyanLCampos.algafood_api.api.controller;

import static com.github.RyanLCampos.algafood_api.infra.repository.spec.RestauranteSpecs.*;

import com.github.RyanLCampos.algafood_api.domain.model.Restaurante;
import com.github.RyanLCampos.algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    private RestauranteRepository repository;

    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNomeFrete(String nome,
                                                      BigDecimal taxaFreteInicial,
                                                      BigDecimal taxaFreteFinal){
        return repository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome){

        return repository.findComFreteGratis(nome);
    }

    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restaurantePrimeiro() {
        return repository.buscarPrimeiro();
    }

}
