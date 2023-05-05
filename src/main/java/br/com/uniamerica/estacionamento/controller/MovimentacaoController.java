package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movimentacao/")
public class MovimentacaoController {
//
//    @Autowired
//    private MovimentacaoRepository service;
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public Optional<Movimentacao> findById(
//            @PathVariable(value = "id") String id
//    ){
//        return service.findById(Long.valueOf(id));
//    };
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Movimentacao> findAll( ){
//        return service.findAll();
//    };
//    @RequestMapping(value = "/create", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> create(@RequestBody final Movimentacao movimentacao){
//        this.service.save(movimentacao);
//        return ResponseEntity.ok("config criada");
//    }
//    @RequestMapping(value = "/modelo/atualiza", method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> update(@RequestBody Movimentacao movimentacao){
//        this.service.save(movimentacao);
//        return ResponseEntity.ok("registro atualizado");
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//
//    public ResponseEntity<?> delete(   @PathVariable (value = "id") Long id){
//        final Movimentacao movDeletado = this.service.findById(id).orElse(null);
//
//        this.service.delete(movDeletado);
//        return ResponseEntity.ok("registro excluido");
//    }
//

}
