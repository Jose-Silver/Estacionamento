package br.com.uniamerica.estacionamento.controller.exeption;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String message) {
        super(message);
    }
}