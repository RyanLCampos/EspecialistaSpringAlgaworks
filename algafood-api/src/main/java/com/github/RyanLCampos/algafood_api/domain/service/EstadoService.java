package com.github.RyanLCampos.algafood_api.domain.service;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeEmUsoException;
import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Estado;
import com.github.RyanLCampos.algafood_api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> obterTodos(){
        return estadoRepository.findAll();
    }

    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public Estado porId(Long id){
        Optional<Estado> estadoOptional = estadoRepository.findById(id);

        if(estadoOptional.isEmpty()){
            throw new EntidadeNaoEncontradoException("Estado não cadastrado.");
        }

        return estadoOptional.get();
    }

    public void deletar(Long estadoId){
        try{
            Estado estado = porId(estadoId);

            estadoRepository.delete(estado);

        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("O estado de ID " + estadoId + " não pode ser removido, pois está associado a uma ou mais cidades.");
        }
    }

}
