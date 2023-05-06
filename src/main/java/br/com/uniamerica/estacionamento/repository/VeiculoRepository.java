package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {
//    @Modifying
//    @Query("UPDATE veiculos c SET c.ativo = false WHERE c.id = :id")
//    void desativar(@Param("id") Long id);
@Query("SELECT c FROM Veiculo c WHERE c.plate = :plate")
List<Veiculo> findByPlate(@Param("plate") String plate);


//    void update(Veiculo veiculo);
}
