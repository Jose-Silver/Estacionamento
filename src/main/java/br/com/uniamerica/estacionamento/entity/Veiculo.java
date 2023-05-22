package br.com.uniamerica.estacionamento.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.Banner;

import java.util.List;

@Entity
@Table(name = "veiculo", schema = "public")
public class Veiculo extends AbstractEntity{
    @Getter @Setter
    @Column(name = "placa",nullable = false,unique = true, length = 10)
    private String plate;
    @Getter @Setter
    @Column(name = "ano",nullable = false)
    private int ano;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "cor", nullable = false, length = 20)
    private Cor cor;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "tipo", nullable = false, length = 6)
    private Tipo tipo;


    public Veiculo() {
    }

    // Getter and setter for movimentacoes

    public Veiculo(String plate, Modelo modelo, Cor cor, Tipo tipo, Integer ano) {
        this.plate = plate;
        this.modelo = modelo;
        this.cor = cor;
        this.tipo = tipo;
        this.ano = ano;
    }
}