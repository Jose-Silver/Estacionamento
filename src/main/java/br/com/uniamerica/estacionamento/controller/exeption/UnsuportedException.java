package br.com.uniamerica.estacionamento.controller.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedException extends RuntimeException{

    private static long serialVersionUID = 1l;


}
