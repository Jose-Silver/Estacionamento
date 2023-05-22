package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.dtos.VeiculoDTOS;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public ResponseEntity<?> findById (Long id) {
        Optional < Veiculo> veiculoOptional =veiculoRepository.findById(id);
        if (veiculoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("veiculo inexistente");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(veiculoOptional.get());

        }
    }


    public ResponseEntity<?> findAll( ) {
        List<Veiculo> veiculoes = veiculoRepository.findAll();
        return  ResponseEntity.ok().body(veiculoes);
    }

    public ResponseEntity<?> findAtivo( ) {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<Veiculo> veiculosAtivos = new ArrayList<>();
        veiculos.forEach(veiculo -> {if(veiculo.isAtivo()){
            veiculosAtivos.add(veiculo);
        }
        });
        return  ResponseEntity.ok().body(veiculosAtivos);
    }

    public ResponseEntity<?> update(Long id, VeiculoDTOS veiculoDTOS) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        if (optionalVeiculo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found with ID: " + id);
        } else {
            Veiculo veiculo = optionalVeiculo.get();
            BeanUtils.copyProperties(veiculoDTOS, veiculo);
            veiculo.setAtualizacao(LocalDateTime.now());
            veiculoRepository.save(veiculo);
            return ResponseEntity.ok().body("Veiculo atualizado com sucesso");
        }
    }

    @Transactional
    public ResponseEntity<?> create(Veiculo veiculo) {

        if ( veiculoRepository.findByPlate(veiculo.getPlate()).isEmpty() ) {


            try {

                veiculo.setAtivo(true);
                veiculoRepository.save(veiculo);
                TransactionAspectSupport.currentTransactionStatus().flush();
                return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());

            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("placa ja cadastrada");
        }
    }

    public ResponseEntity<?> delete (Long id) {

        Optional<Veiculo> optionalVeiculo = this.veiculoRepository.findById(id);

        if(optionalVeiculo.isPresent()) {
            Veiculo veiculo = veiculoRepository.getById(optionalVeiculo.get().getId());
            if (movimentacaoRepository.findByVeiculoId(id).isEmpty()){

                veiculoRepository.delete(veiculo);
                return ResponseEntity.ok().build();
            } else {

                veiculo.setAtivo(false);
                this.veiculoRepository.save(veiculo);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
