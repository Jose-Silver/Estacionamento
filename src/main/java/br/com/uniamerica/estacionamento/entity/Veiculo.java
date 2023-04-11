package br.com.uniamerica.estacionamento.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "veiculos", schema = "public")
public class Veiculo extends AbstractEntity {
    @Getter @Setter
    @Column(name = "plate", nullable = false, unique = true)
    private String plate;
    @Getter @Setter
    @JoinColumn(name="modelo", insertable = false, updatable=false)
    @ManyToOne
    private Modelo modelo;
    @Getter @Setter
    @Column(name = "cor", nullable = false)
    @Enumerated(EnumType.STRING)
    private Cor cor;
    @Getter @Setter
    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Getter @Setter
    @Column(name = "ano", nullable = false)
    private Integer Ano;

}