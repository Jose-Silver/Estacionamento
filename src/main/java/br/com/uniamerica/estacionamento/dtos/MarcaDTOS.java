package br.com.uniamerica.estacionamento.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MarcaDTOS {

    @NotBlank
    @Size(max = 50, message = "Erro: nome da marca nao pode ser maior que 50 caracteres")
    private String nome;

    public String getNome() {
        return nome;
    }

    public MarcaDTOS(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
