package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.dtos.ModeloDTOS;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository repository;
    @Autowired
    private MarcaRepository marcarepository;

    @Transactional
    public ResponseEntity<?> findById (Long id) {
        Optional < Modelo> modeloOptional =repository.findById(id);
        if (modeloOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modelo inexistente");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(modeloOptional.get());

        }
    }

    @Transactional
    public ResponseEntity<?> findAll( ) {
        List<Modelo> modeloes = repository.findAll();
        return  ResponseEntity.ok().body(modeloes);
    }

    public ResponseEntity<?> findAtivo( ) {
        List<Modelo> modelos = repository.findAll();
        List<Modelo> ModelosAtivos = new ArrayList<>();
        modelos.forEach(modelo -> {if(modelo.isAtivo()){
            ModelosAtivos.add(modelo);
        }
        });
        return  ResponseEntity.ok().body(ModelosAtivos);
    }




    @Transactional
    public ResponseEntity<?> update(Long id, ModeloDTOS modeloDTOS) {
        Optional<Modelo> optionalModelo = repository.findById(id);
        if (optionalModelo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo not found with ID: " + id);
        } else {
            Modelo modelo = optionalModelo.get();
            //BeanUtils.copyProperties(modeloDTOS, modelo);
            modelo.setNome(modeloDTOS.getNome());
            modelo.setAtualizacao(LocalDateTime.now());
            repository.save(modelo);
            return ResponseEntity.ok().body("Modelo atualizado com sucesso");
        }
    }

    @Transactional
    public ResponseEntity<?> create(ModeloDTOS modeloDTOS) {


        try {

            Modelo modelo = new Modelo();
            modelo.setNome(modeloDTOS.getNome());
            Long marca_id = modeloDTOS.getMarca();
            Marca marca1 = marcarepository.getById(marca_id);
            modelo.setMarca(marca1);
            modelo.setAtualizacao(LocalDateTime.now());
            modelo.setAtivo(true);
            repository.save(modelo);
            TransactionAspectSupport.currentTransactionStatus().flush();
            return ResponseEntity.status(HttpStatus.CREATED).body(modelo);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());

        }
    }
    @Transactional
    public ResponseEntity<?> delete (Long id) {

        Optional<Modelo> optionalModelo = this.repository.findById(id);

        if(optionalModelo.isPresent()) {
            Modelo modelo = repository.getById(optionalModelo.get().getId());


            repository.delete(modelo);
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
