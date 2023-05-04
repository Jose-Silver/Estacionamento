package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/condutor/")
public class CondutorController {

    @Autowired
    private CondutorController service;
    @Autowired
    private br.com.uniamerica.estacionamento.repository.CondutorRepository condutorRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public Optional<Condutor> findById(
            @PathVariable(value = "id") Long id
    ){
        return service.findById(Long.valueOf(id));
    };

    @RequestMapping(value = "/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public List<Condutor> findAll( ){
        return service.findAll();
    };

    @RequestMapping(value = "/ativo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public List<Condutor> findAtivo( ){
        List<Condutor> Condutores = service.findAll();
        return Condutores.stream().filter(Condutor::isAtivo).collect(Collectors.toList());
    };


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public ResponseEntity<?> create(@RequestBody final Condutor Condutor){
        this.service.create(Condutor);
        return ResponseEntity.ok("Marca criada");
    }
    @RequestMapping(value = "/atualiza", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public ResponseEntity<?> update(@RequestBody Condutor Condutor){
        this.service.update(Condutor);
        return ResponseEntity.ok("registro atualizado");
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable final Long id) {
        Optional<Condutor> optionalCondutor = this.service.findById(id);

        if(optionalCondutor.isPresent()) {
            Condutor condutor = optionalCondutor.get();
            if (movimentacaoRepository.findByCondutorId(id) == null){

                this.service.deletar(condutor.getId());
                return ResponseEntity.ok().build();
            } else {

                condutor.setAtivo(false);
                this.service.update(condutor);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
