package br.com.uniamerica.estacionamento.dtos;

import br.com.uniamerica.estacionamento.entity.Cor;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Tipo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class VeiculoDTOS {

    @Autowired
    private ModeloRepository modeloRepository;
    @NotBlank(message = "paca nao pode ser nulo")
    private String plate;
    @NotBlank(message = "modelo nao pode ser nulo")
    private Modelo modelo;
    @NotBlank(message = "plate nao pode ser nulo")
    private Cor cor;
    @NotBlank(message = "tipo nao pode ser nulo")
    private Tipo tipo;
    @NotBlank(message = "ano nao pode ser nulo")
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

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
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

    public VeiculoDTOS(String plate, Long modelo_id, Cor cor, Tipo tipo, int ano) {
        this.plate = plate;
        this.modelo = modeloRepository.getById(modelo_id);
        this.cor = cor;
        this.tipo = tipo;
        this.ano = ano;
    }
}
