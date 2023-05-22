package br.com.uniamerica.estacionamento.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarcaDTOS {

    @NotBlank(message = "Erro: nome da marca nao pode estar vazio")
    @Size(max = 50, message = "Erro: nome da marca nao pode ser maior que 50 caracteres")
    private String nome;




}
