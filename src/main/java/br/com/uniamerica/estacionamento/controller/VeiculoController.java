package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VeiculoController {
//
//    @Autowired
//    private VeiculoRepository service;
//    @Autowired
//    private MovimentacaoRepository movimentacaoservice;
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public Optional<Veiculo> findById(
//            @PathVariable(value = "id") Long id
//    ){
//        return service.findById(Long.valueOf(id));
//    };
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Veiculo> findAll( ){
//        return service.findAll();
//    };
//
//    @RequestMapping(value = "/ativo", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Veiculo> findAtivo( ){
//        List<Veiculo> veiculos = service.findAll();
//        return veiculos.stream().filter(Veiculo::isAtivo).collect(Collectors.toList());
//    };
//
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> create(@RequestBody final Veiculo veiculo){
//        this.create(veiculo);
//        this.service.save(veiculo);
//        return ResponseEntity.ok("veiculo criado");
//    }
//    @RequestMapping(value = "/atualiza", method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> update(@RequestBody Veiculo veiculo){
//        this.service.update(veiculo);
//        return ResponseEntity.ok("registro atualizado");
//    }
//
//
//
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deletar(@PathVariable final Long id) {
//        Optional<Veiculo> optionalCondutor = this.service.findById(id);
//
//        if(optionalCondutor.isPresent()) {
//            Veiculo veiculo = optionalCondutor.get();
//            //if(veiculo.getMovimentacoes().isEmpty())
//            if( movimentacaoservice.findByCondutorId(id) == null){
//
//                this.service.delete(veiculo);
//                return ResponseEntity.ok().build();
//            } else {
//
//                veiculo.setAtivo(false);
//                this.service.save(veiculo);
//                return ResponseEntity.ok().build();
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//

}
