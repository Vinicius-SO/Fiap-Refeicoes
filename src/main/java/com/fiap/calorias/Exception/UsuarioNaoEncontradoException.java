package com.fiap.calorias.Exception;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
