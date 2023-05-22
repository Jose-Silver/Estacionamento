package br.com.uniamerica.estacionamento.dtos;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
@NoArgsConstructor
@Data
public class ModeloDTOS {

    @Getter @Setter
    @NotBlank(message = "Erro: nome nao pde ser nulo")
    @Size(max=50, message = "Erro: nome nao pode passar de 50 caracteres")
    private String nome;
    @Getter @Setter
    @NotNull(message = " Erro: o modelo deve possuir uma marca")
    private Long marca;


    public ModeloDTOS(String nome, Long marca) {
        this.nome = nome;
        this.marca = marca;
    }
}
