package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/config/")
public class ConfiguracaoController {
//
//
//    @Autowired
//    private ConfiguracaoRepository service;
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public Optional<Configuracao> findById(
//            @PathVariable(value = "id") String id
//    ){
//        return service.findById(Long.valueOf(id));
//    };
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Configuracao> findAll( ){
//        return service.findAll();
//    };
//    @RequestMapping(value = "/create", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> create(@RequestBody final Configuracao Configuracao){
//        this.service.save(Configuracao);
//        return ResponseEntity.ok("config criada");
//    }
//    @RequestMapping(value = "/modelo/atualiza", method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> update(@RequestBody Configuracao Configuracao){
//        this.service.save(Configuracao);
//        return ResponseEntity.ok("registro atualizado");
//    }
}
