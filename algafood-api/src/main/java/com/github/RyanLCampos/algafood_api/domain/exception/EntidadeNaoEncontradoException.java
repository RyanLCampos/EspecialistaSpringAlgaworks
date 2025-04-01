package com.github.RyanLCampos.algafood_api.domain.exception;

public class EntidadeNaoEncontradoException extends RuntimeException{

    public EntidadeNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
