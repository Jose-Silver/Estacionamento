package br.com.uniamerica.estacionamento.controller.exeption;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
private static final long serialVersionUID = 1l;
    @Getter @Setter
private Date timestamp;
    @Getter @Setter
private String message;
    @Getter @Setter
private String detail;

    public ExceptionResponse(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }
}
