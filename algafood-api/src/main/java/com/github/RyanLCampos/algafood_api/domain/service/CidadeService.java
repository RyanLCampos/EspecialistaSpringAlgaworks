package com.github.RyanLCampos.algafood_api.domain.service;

import com.github.RyanLCampos.algafood_api.domain.exception.EntidadeNaoEncontradoException;
import com.github.RyanLCampos.algafood_api.domain.model.Cidade;
import com.github.RyanLCampos.algafood_api.domain.model.Estado;
import com.github.RyanLCampos.algafood_api.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public Cidade salvar(Cidade cidade){

        Long estadoId = cidade.getEstado().getId();

        existeEstado(estadoId);

        return cidadeRepository.save(cidade);
    }

    public List<Cidade> obterTodos(){
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> obterPorId(Long id){
        return cidadeRepository.findById(id);
    }

    public void deletar(Cidade cidade){
        cidadeRepository.delete(cidade);
    }

    private void existeEstado(Long estadoId){
        Estado estado = estadoService.porId(estadoId);

        if(estado == null){
           throw new EntidadeNaoEncontradoException(
                   String.format("Não existe estado cadastrado com código: %d", estadoId)
           );
        }
    }

}
