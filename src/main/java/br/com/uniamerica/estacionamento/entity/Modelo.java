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

        @ManyToOne
        @JoinColumn(nullable = false, name = "marca")
        @Getter @Setter
        private Marca marca;

        public Modelo() {
        }




        public Modelo(String nome) {
            this.nome = nome;
        }
    }