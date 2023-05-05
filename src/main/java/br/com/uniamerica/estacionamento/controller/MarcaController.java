package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marca/")
public class MarcaController {
//
//
//    @Autowired
//    private MarcaRepository service;
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public Optional<Marca> findById(
//            @PathVariable(value = "id") String id
//    ){
//        return service.findById(Long.valueOf(id));
//    };
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Marca> findAll( ){
//        return service.findAll();
//    };
//
//
//    @RequestMapping(value = "/active", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Marca> findActive( ){
//        List<Marca> Marcas = service.findAll();
//        return Marcas.stream().filter(Marca::isAtivo).collect(Collectors.toList());
//    };
//
//
//
//
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> create(@RequestBody final Marca Marca){
//        this.service.save(Marca);
//        return ResponseEntity.ok("Marca criada");
//    }
//
//
//    @RequestMapping(value = "/modelo/atualiza", method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> update(@RequestBody Marca Marca){
//        this.service.save(Marca);
//        return ResponseEntity.ok("registro atualizado");
//    }
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//
//    public ResponseEntity<?> delete(   @PathVariable (value = "id") Long id){
//        final Marca MarcaDeletado = this.service.findById(id).orElse(null);
//
//        this.service.delete(MarcaDeletado);
//        return ResponseEntity.ok("registro excluido");
//    }


}
