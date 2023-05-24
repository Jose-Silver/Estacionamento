package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByCondutorId(Long condutorId);
    List<Movimentacao> findByVeiculoId(Long condutorId);
    List<Movimentacao> findByVeiculoPlate(String plate);

}
