package br.com.uniamerica.estacionamento.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes", schema = "public")
    public class Movimentacao extends AbstractEntity {
        @Getter
        @JoinColumn(name = "veiculo", nullable = false, unique = true)
        @ManyToOne
        private Veiculo veiculo;
        @Getter
        @JoinColumn(name = "condutor", nullable = false)
        @ManyToOne
        private Condutor condutor;
        @Getter
        @Column(name = "entrada_veiculo", nullable = false)
        private LocalDateTime entrada;
        @Getter
        @Column(name = "saida_veiculo")
        private LocalDateTime saida;
        @Getter
        @Column(name = "tempo")
        private LocalDate tempo;
        @Getter
        @Column(name = "tempo_Desconto")
        private LocalDate tempoDesconto;
        @Getter
        @Column(name = "tempo_multa")
        private LocalDate tempoMulta;
        @Getter
        @Column(name = "valor_desconto")
        private BigDecimal valorDesconto;
        @Getter
        @Column(name = "valor_multa")
        private BigDecimal valorMulta;
        @Getter
        @Column(name = "valot_total")
        private BigDecimal valorTotal;
        @Getter
        @Column(name = "valor_hora")
        private BigDecimal valorHora;
        @Getter
        @Column(name = "valorHoraMulta")
        private BigDecimal valorHoraMulta;
        public Movimentacao() {
        }


    }

