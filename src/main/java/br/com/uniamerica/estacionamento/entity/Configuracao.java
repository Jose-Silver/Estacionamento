package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "configuracoes", schema = "public")
public class Configuracao extends AbstractEntity {
@Column(name = "valor_hora")
    @Getter @Setter
    private BigDecimal ValorHora;
    @Column(name = "valor_multa")
    @Getter @Setter
    private BigDecimal ValorMulta;
    @Column(name = "inicio_expediente")
    @Getter @Setter
    private LocalDateTime InicioExpediente;
    @Column(name = "tempo_parado_desconto")
    @Getter @Setter
    private LocalDateTime TempoParadoDesconto;
    @Column(name = "tempo_de_desconto")
    @Getter @Setter
    private LocalDateTime TempoDeDesconto;
    @Column(name = "gerar_desconto")
    @Getter @Setter
    private Boolean GerarDesconto;
    @Column(name = "vagas_moto")
    @Getter @Setter
    private Integer VagasMoto;
    @Column(name = "vagas_carro")
    @Getter @Setter
    private Integer VagasCarro;
    @Column(name = "vagas_vam")
    @Getter @Setter
    private Integer VagasVam;

}
