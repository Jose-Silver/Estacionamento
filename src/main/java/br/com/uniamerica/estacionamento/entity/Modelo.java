    package br.com.uniamerica.estacionamento.entity;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Table(name = "modelos", schema = "public")
    @AllArgsConstructor
    @NoArgsConstructor
    public class Modelo extends AbstractEntity {
        @Column(name = "nome", unique = true)
        @Getter @Setter
        private String nome;

        @ManyToOne
        @Getter @Setter
        @JoinColumn(nullable = false, name = "marca")
        private Marca marca;







    }