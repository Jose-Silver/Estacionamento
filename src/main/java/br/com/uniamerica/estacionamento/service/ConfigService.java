package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
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
public class ConfigService {

    @Autowired
    private ConfiguracaoRepository configrepository;


    public ResponseEntity<?> findById (Long id) {
        Optional < Configuracao> configuracaoOptional =configrepository.findById(id);
        if (configuracaoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("configuracao inexistente");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(configuracaoOptional.get());

        }
    }


    public ResponseEntity<?> findAll( ) {
        List<Configuracao> configuracaoes = configrepository.findAll();
        return  ResponseEntity.ok().body(configuracaoes);
    }


    public ResponseEntity<?> update(Long id, Configuracao configuracaoDTOS) {
        Optional<Configuracao> optionalConfiguracao = configrepository.findById(id);
        if (optionalConfiguracao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Configuracao not found with ID: " + id);
        } else {
            Configuracao configuracao1 = optionalConfiguracao.get();
            BeanUtils.copyProperties(configuracaoDTOS, configuracao1);
            configuracao1.setAtualizacao(LocalDateTime.now());
            configrepository.save(configuracao1);
            return ResponseEntity.ok().body("Configuracao atualizado com sucesso");
        }
    }

    @Transactional
    public ResponseEntity<?> create(Configuracao configuracao) {

            try {
                configuracao.setAtivo(true);
                configrepository.save(configuracao);
                TransactionAspectSupport.currentTransactionStatus().flush();
                return ResponseEntity.status(HttpStatus.CREATED).body(configuracao);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());

            }

    }

    public ResponseEntity<?> delete (Long id) {

        Optional<Configuracao> optionalConfiguracao = this.configrepository.findById(id);

        if(optionalConfiguracao.isPresent()) {
            Configuracao configuracao = configrepository.getById(optionalConfiguracao.get().getId());


                configrepository.delete(configuracao);
                return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
