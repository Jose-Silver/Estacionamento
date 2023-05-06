package br.com.uniamerica.estacionamento.dtos;

import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class MovimentacaoDTOS {


   @NotBlank(message = "veiculo_id nao pode ser inexistente")
    private Long veiculo_id;
    @NotBlank(message = "condutor_id nao pode ser inexistente")

    private Long condutor_id;
    @NotBlank(message = "entrada nao pode ser inexistente")

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private LocalTime tempo;
    private LocalTime tempoDesconto;
    private LocalTime tempoMulta;
    private BigDecimal valorDesconto;
    private BigDecimal valorMulta;
    private BigDecimal valorTotal;
    private BigDecimal valorHora;
    private BigDecimal valorHoraMulta;


    public MovimentacaoDTOS(CondutorRepository condutor_idRepository, VeiculoRepository veiculo_idRepository, Long veiculo_id, Long condutor_id, LocalDateTime entrada, LocalDateTime saida, LocalTime tempo, LocalTime tempoDesconto, LocalTime tempoMulta, BigDecimal valorDesconto, BigDecimal valorMulta, BigDecimal valorTotal, BigDecimal valorHora, BigDecimal valorHoraMulta) {
        this.veiculo_id = veiculo_id;
        this.condutor_id = condutor_id;
        this.entrada = entrada;
        this.saida = saida;
        this.tempo = tempo;
        this.tempoDesconto = tempoDesconto;
        this.tempoMulta = tempoMulta;
        this.valorDesconto = valorDesconto;
        this.valorMulta = valorMulta;
        this.valorTotal = valorTotal;
        this.valorHora = valorHora;
        this.valorHoraMulta = valorHoraMulta;
    }


    public Long getVeiculo() {
        return veiculo_id;
    }

    public void setVeiculo(Long veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Long getCondutor() {
        return condutor_id;
    }

    public void setCondutor(Long condutor_id) {
        this.condutor_id = condutor_id;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalTime tempo) {
        this.tempo = tempo;
    }

    public LocalTime getTempoDesconto() {
        return tempoDesconto;
    }

    public void setTempoDesconto(LocalTime tempoDesconto) {
        this.tempoDesconto = tempoDesconto;
    }

    public LocalTime getTempoMulta() {
        return tempoMulta;
    }

    public void setTempoMulta(LocalTime tempoMulta) {
        this.tempoMulta = tempoMulta;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public BigDecimal getValorHoraMulta() {
        return valorHoraMulta;
    }

    public void setValorHoraMulta(BigDecimal valorHoraMulta) {
        this.valorHoraMulta = valorHoraMulta;
    }
}
