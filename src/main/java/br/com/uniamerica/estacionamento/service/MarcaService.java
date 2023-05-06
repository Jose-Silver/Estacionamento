package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.dtos.MarcaDTOS;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
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
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    @Transactional
    public ResponseEntity<?> findById (Long id) {
        Optional < Marca> marcaOptional =repository.findById(id);
        if (marcaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("marca inexistente");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(marcaOptional.get());

        }
    }

    @Transactional
    public ResponseEntity<?> findAll( ) {
        List<Marca> marcaes = repository.findAll();
        return  ResponseEntity.ok().body(marcaes);
    }

    public ResponseEntity<?> findAtivo( ) {
        List<Marca> marcas = repository.findAll();
        List<Marca> marcasAtivas = new ArrayList<>();
        marcas.forEach(marca -> {if(marca.isAtivo()){
            marcasAtivas.add(marca);
        }
        });

        return  ResponseEntity.ok().body(marcasAtivas);
    }
    @Transactional

    public ResponseEntity<?> update(Long id, MarcaDTOS marcaDTOS) {
        Optional<Marca> optionalMarca = repository.findById(id);
        if (optionalMarca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Marca not found with ID: " + id);
        } else {
            Marca marca1 = optionalMarca.get();
            BeanUtils.copyProperties(marcaDTOS, marca1);
            marca1.setAtualizacao(LocalDateTime.now());
            repository.save(marca1);
            return ResponseEntity.ok().body("Marca atualizado com sucesso");
        }
    }

    @Transactional
    public ResponseEntity<?> create(Marca marca) {

        try {
            marca.setAtivo(true);
            repository.save(marca);
            TransactionAspectSupport.currentTransactionStatus().flush();
            return ResponseEntity.status(HttpStatus.CREATED).body(marca);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.badRequest().body(e.toString());
        }

    }
    @Transactional
    public ResponseEntity<?> delete (Long id) {

        Optional<Marca> optionalMarca = this.repository.findById(id);

        if(optionalMarca.isPresent()) {
            Marca marca = repository.getById(optionalMarca.get().getId());


            repository.delete(marca);
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
