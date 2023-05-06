package br.com.uniamerica.estacionamento.dtos;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModeloDTOS {
    @NotBlank(message = "Erro: nome nao pode ser nulo")
    @Size(max=50, message = "Erro: nome nao pode passar de 50 caracteres")
    private String nome;
    @NotBlank(message = " Erro: o modelo deve possuir uma marca")
    private Long marca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ModeloDTOS(String nome, Long marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public Long getMarca() {
        return marca;
    }

    public void setMarca(Long marca) {
        this.marca = marca;
    }
}
