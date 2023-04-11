    package br.com.uniamerica.estacionamento.entity;


    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Entity
    @Table(name = "modelos", schema = "public")
    public class Modelo extends AbstractEntity {
        @Column(name = "nome", nullable = false, unique = true)
        @Getter @Setter
        private String nome;

        @Enumerated(EnumType.STRING)
        @Column(name = "marca", nullable = false)
        private Marcas marca;

        public Modelo() {
        }



    }