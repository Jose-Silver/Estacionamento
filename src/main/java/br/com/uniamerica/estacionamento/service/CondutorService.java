package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.controller.CondutorController;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.validator.cfg.defs.AssertTrueDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public ResponseEntity<?> findById ( Long id) {
        Optional < Condutor> condutorOptional =condutorRepository.findById(id);
        if (condutorOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("condutor nao existe");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(condutorOptional.get());

        }
    }
    @Transactional
    public ResponseEntity<?> save(Condutor condutor) {
        try {
            condutor.setAtivo(true);
            condutorRepository.save(condutor);
            TransactionAspectSupport.currentTransactionStatus().flush();
            return ResponseEntity.status(HttpStatus.CREATED).body(condutor);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

//    public ResponseEntity<?> create(Condutor Condutor){
//        condutorRepository.
//    }

}
