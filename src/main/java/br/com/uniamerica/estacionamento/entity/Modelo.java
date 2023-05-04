    package br.com.uniamerica.estacionamento.entity;


    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Entity
    @Table(name = "modelos", schema = "public")
    public class Modelo extends AbstractEntity {
        @Column(name = "nome", unique = true)
        @Getter @Setter
        private String nome;

        public Modelo() {
        }

        /** @Enumerated(EnumType.STRING)
        @Column(name = "marca", nullable = false)
        private Marcas marca;**/


        public Modelo(String nome) {
            this.nome = nome;
        }
    }