package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id", nullable = false, unique = true, insertable=false, updatable=false)
    private Long id;
    @Getter @Setter
    @Column(name = "local-date-time", nullable = false, unique = true)
    private LocalDateTime cadastro;
    @Getter @Setter
    @Column(name = "atualizacao", nullable = false, unique = true)
    private LocalDateTime atualizacao;
    @Getter @Setter
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    protected AbstractEntity() {
    }

    @PrePersist
    private void prePersist() {
        this.cadastro = LocalDateTime.now();
    }



    private void preUpdate() {
        this.atualizacao = LocalDateTime.now();
    }

}
