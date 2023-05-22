package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.dtos.MovimentacaoDTOS;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.*;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
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
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CondutorRepository condutorRepository;

    @Transactional
    public ResponseEntity<?> findById (Long id) {
        Optional < Movimentacao> movimentacaoOptional =repository.findById(id);
        if (movimentacaoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movimentacao inexistente");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(movimentacaoOptional.get());

        }
    }

    @Transactional
    public ResponseEntity<?> findAll( ) {
        List<Movimentacao> movimentacaoes = repository.findAll();
        return  ResponseEntity.ok().body(movimentacaoes);
    }



    @Transactional
    public ResponseEntity<?> update(Long id, MovimentacaoDTOS movimentacaoDTOS) {
        Optional<Movimentacao> optionalMovimentacao = repository.findById(id);
        Condutor optionalCondutor = movimentacaoDTOS.getCondutor();
        Veiculo optionalVeiculo = movimentacaoDTOS.getVeiculo();
        if (optionalCondutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor not found with ID: " + id);
        }
        if (optionalVeiculo == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found with ID: " + id);
        }

        if (optionalMovimentacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentacao not found with ID: " + id);
        } else {

            Movimentacao movimentacao1 = optionalMovimentacao.get();
//            Veiculo veiculo1 = optionalVeiculo.get();
            BeanUtils.copyProperties(movimentacaoDTOS,movimentacao1);
            movimentacao1.setAtivo(true);
//            movimentacao1.setCondutor(condutor1);
//            movimentacao1.setVeiculo(veiculo1);
            movimentacao1.setAtualizacao(LocalDateTime.now());
            movimentacao1.setEntrada(LocalDateTime.now());
            repository.save(movimentacao1);
            return ResponseEntity.ok().body("Movimentacao atualizado com sucesso");
        }
    }

    @Transactional
    public ResponseEntity<?> create(MovimentacaoDTOS movimentacaoDTOS) {
       Condutor optionalCondutor = movimentacaoDTOS.getCondutor();
        Veiculo optionalVeiculo = movimentacaoDTOS.getVeiculo();
        if (optionalCondutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor not found ");
        }
        if (optionalVeiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found");
        }

            try {
                Movimentacao movimentacao1 = new Movimentacao();
//            Condutor condutor1 = optionalCondutor.get();
//            Veiculo veiculo1 = optionalVeiculo.get();
                BeanUtils.copyProperties(movimentacaoDTOS, movimentacao1);
                movimentacao1.setAtivo(true);
//            movimentacao1.setCondutor(condutor1);
//            movimentacao1.setVeiculo(veiculo1);
                movimentacao1.setAtualizacao(LocalDateTime.now());
                movimentacao1.setEntrada(LocalDateTime.now());
                repository.save(movimentacao1);
                return ResponseEntity.ok().body("Movimentacao atualizado com sucesso");
            } catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());

            }
        }



    @Transactional
    public ResponseEntity<?> delete (Long id) {

        Optional<Movimentacao> optionalMovimentacao = this.repository.findById(id);

        if(optionalMovimentacao.isPresent()) {
            Movimentacao movimentacao = repository.getById(optionalMovimentacao.get().getId());


            movimentacao.setAtivo(false);
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }





    }

}
