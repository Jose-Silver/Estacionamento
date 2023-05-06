package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.dtos.CondutorDTOS;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
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
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public ResponseEntity<?> findById (Long id) {
        Optional < Condutor> condutorOptional =condutorRepository.findById(id);
        if (condutorOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("condutor inexistente");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(condutorOptional.get());

        }
    }


    public ResponseEntity<?> findAll( ) {
        List<Condutor> condutores = condutorRepository.findAll();
        return  ResponseEntity.ok().body(condutores);
    }

    public ResponseEntity<?> findAtivo( ) {
        List<Condutor> condutores = condutorRepository.findAll();
        List<Condutor> condutoresAtivos = new ArrayList<>();
        condutores.forEach(condutor -> {if(condutor.isAtivo()){
            condutoresAtivos.add(condutor);
        }
        });

        return  ResponseEntity.ok().body(condutoresAtivos);
    }


    public ResponseEntity<?> update(Long id, CondutorDTOS condutorDTOS) {
        Optional<Condutor> optionalCondutor = condutorRepository.findById(id);
        if (optionalCondutor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor not found with ID: " + id);
        } else {
            Condutor condutor = optionalCondutor.get();
            BeanUtils.copyProperties(condutorDTOS, condutor);
            condutor.setAtualizacao(LocalDateTime.now());
            condutorRepository.save(condutor);
            return ResponseEntity.ok().body("Condutor atualizado com sucesso");
        }
    }

    @Transactional
    public ResponseEntity<?> create(Condutor condutor) {

        if ( condutorRepository.findByCpf(condutor.getCpf()).isEmpty() ) {


            try {
                condutor.setAtivo(true);
                condutor.setTempoPago(LocalTime.of(0, 0));
                condutor.setTempoDesconto(LocalTime.of(0, 0));
                condutorRepository.save(condutor);
                TransactionAspectSupport.currentTransactionStatus().flush();
                return ResponseEntity.status(HttpStatus.CREATED).body(condutor);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseEntity.badRequest().body(e.toString());
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF ja cadastrado");
        }
    }

public ResponseEntity<?> delete (Long id) {

    Optional<Condutor> optionalCondutor = this.condutorRepository.findById(id);

    if(optionalCondutor.isPresent()) {
        Condutor condutor = condutorRepository.getById(optionalCondutor.get().getId());
        if (movimentacaoRepository.findByCondutorId(id).isEmpty()){

            condutorRepository.delete(condutor);
            return ResponseEntity.ok().build();
        } else {

            condutor.setAtivo(false);
            this.condutorRepository.save(condutor);
            return ResponseEntity.ok().build();
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
