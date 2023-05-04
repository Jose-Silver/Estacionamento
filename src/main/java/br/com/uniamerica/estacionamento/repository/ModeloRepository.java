package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.util.Optional;
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    @Modifying
    @Query("UPDATE modelos c SET c.ativo = false WHERE c.id = :id")
    void desativar(@Param("id") Long id);

}


