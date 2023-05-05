package br.com.uniamerica.estacionamento.controller;


import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modelo/")
public class ModeloController {
//
//    @Autowired
//    private ModeloRepository service;
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
//  produces = MediaType.APPLICATION_JSON_VALUE )
//
//  public Optional<Modelo> findById(
//          @PathVariable (value = "id") String id
//  ){
//        return service.findById(Long.valueOf(id));
//  };
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Modelo> findAll( ){
//        return service.findAll();
//    };
//
//    @RequestMapping(value = "/active", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Modelo> findActive( ){
//        List<Modelo> Modelos = service.findAll();
//        return Modelos.stream().filter(Modelo::isAtivo).collect(Collectors.toList());
//    };
//
//
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public Modelo create(@RequestBody Modelo modelo){
//      return service.save(modelo);
//    }
//
//
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> update(
//            @PathVariable final Long id,
//            @RequestBody Modelo modelo){
//
//        if(id.equals(modelo.getId())){
//            this.service.save(modelo);
//            return ResponseEntity.ok("Modelo atualizado");
//        }else {
//
//
//            return ResponseEntity.ok("Modelo nao encontrado");
//        }
//    };
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deletar(@PathVariable final Long id) {
//        Optional<Modelo> optionalVeiculo = service.findById(id);
//        if (optionalVeiculo.isPresent()) {
//            Modelo modelo = optionalVeiculo.get();
//            if (modelo.isAtivo()) {
//                this.service.desativar(modelo.getId());
//            } else {
//                return ResponseEntity.badRequest().body("Veiculo esta vinculado a uma movimentacao e nao pode ser deletado");
//            }
//            return ResponseEntity.ok().body("Deletado com Sucesso !!! ");
//        } else {
//            return ResponseEntity.badRequest().body("ID não encontrado.");
//        }
//    }
}



