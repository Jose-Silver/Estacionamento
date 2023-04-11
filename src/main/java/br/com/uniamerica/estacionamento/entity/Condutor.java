package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name="condutores", schema = "public")
public class Condutor extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome",length = 20, unique = true)
    private String nome;
    @Getter @Setter
    @Column(name = "cpf", length = 20, unique = true, nullable = false)
    private String cpf;
    @Getter @Setter
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;
    @Getter @Setter
    @Column(name = "tempo_pago", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime tempoPago;
    @Getter @Setter
    @Column(name = "tempo_desconto", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime tempoDesconto;

}
