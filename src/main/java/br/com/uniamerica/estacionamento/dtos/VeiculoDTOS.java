package br.com.uniamerica.estacionamento.dtos;

import br.com.uniamerica.estacionamento.entity.Cor;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Tipo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class VeiculoDTOS {

    @Autowired
    private ModeloRepository modeloRepository;
    @NotBlank(message = "paca nao pode ser nulo")
    @Size(max = 10)
    private String plate;
    @Getter @Setter
    @NotNull(message = "modelo nao pode ser nulo")
    private Long modelo;
    @NotNull(message = "plate nao pode ser nulo")
    private Cor cor;
    @NotNull(message = "tipo nao pode ser nulo")
    private Tipo tipo;
    @NotNull(message = "ano nao pode ser nulo")
    private int ano;

    public ModeloRepository getModeloRepository() {
        return modeloRepository;
    }

    public void setModeloRepository(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    public String getPlaca() {
        return plate;
    }

    public void setPlaca(String plate) {
        this.plate = plate;
    }


    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public VeiculoDTOS(String plate, Long modelo, Cor cor, Tipo tipo, int ano) {
        this.plate = plate;
        this.modelo = modelo;
        this.cor = cor;
        this.tipo = tipo;
        this.ano = ano;
    }
}
