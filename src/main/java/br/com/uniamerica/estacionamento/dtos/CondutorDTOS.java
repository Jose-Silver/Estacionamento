package br.com.uniamerica.estacionamento.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class CondutorDTOS {
    @NotBlank(message = "Erro: nome em branco")
    private String nome;
    @NotBlank(message = "Erro: cpf em branco")
    private String cpf;
    @NotBlank(message = "Erro: telefone me branco")
    private String telefone;








    public String getNome() {
        return nome;
    }

    public CondutorDTOS(String nome, String cpf, String telefone, LocalTime tempoPago, LocalTime tempoDesconto) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;


    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
