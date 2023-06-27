package br.com.uniamerica.estacionamento.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.time.LocalTime;



@Entity
@Table(name = "configuracao", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class  Configuracao extends AbstractEntity{
    @Getter @Setter
    @Column(name = "valor_hora")
    private BigDecimal valorHora;
    @Getter @Setter
    @Column(name = "valor_minuto_hora")
    private BigDecimal valorMinutoMulta;
    @Getter @Setter
    @Column(name = "inicio_expediente")
    private LocalTime inicioExpediente;
    @Getter @Setter
    @Column(name = "fim_expediente")
    private LocalTime fimExpediente;
    @Getter @Setter
    @Column(name = "tempo_para_desconto")
    private Long  tempoParaDesconto;
    @Getter @Setter
    @Column(name = "tempo_de_desconto")
    private Long   tempoDeDesconto;
    @Getter @Setter
    @Column(name = "gerar_desconto")
    private boolean GerarDesconto;
    @Getter @Setter
    @Column(name = "vagas_carro")
    private int vagasCarro;
    @Getter @Setter
    @Column(name = "vagas_moto")
    private int vagasMoto;
    @Getter @Setter
    @Column(name = "vagas_van")
    private int vagasVan;




    public void init() {
        this.valorHora = BigDecimal.valueOf(10);
        this.valorMinutoMulta = BigDecimal.valueOf(2);
        this.inicioExpediente = LocalTime.of(6,0);
        this.fimExpediente = LocalTime.of(20,0);;
        this.tempoParaDesconto = 50L;
        this.tempoDeDesconto = 5L ;
        this.GerarDesconto = true;
        this.vagasCarro = 50;
        this.vagasMoto = 20;
        this.vagasVan = 5;
    }

    public boolean getGerarDesconto() {
        return this.getGerarDesconto();
    }
}
