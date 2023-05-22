package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.controller.exeption.NotFoundException;
import br.com.uniamerica.estacionamento.dtos.CondutorDTOS;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.service.CondutorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/condutor")
public class CondutorController {

    @Autowired
    private CondutorService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public ResponseEntity<?> findById(
            @PathVariable(value = "id") Long id
    ){
        return service.findById(id);
    };






    @RequestMapping(value = "/all", method = RequestMethod.GET)

    public ResponseEntity<?> findAll( ){
        return service.findAll();
    };

    @RequestMapping(value = "/ativos", method = RequestMethod.GET)
    public ResponseEntity<?> findAtivo( ){
        return service.findAtivo();
    };


    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid  CondutorDTOS condutorDTOS){
        Condutor condutor = new Condutor();
        BeanUtils.copyProperties(condutorDTOS,condutor);

        return service.create(condutor);
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable Long id, @RequestBody  @Valid CondutorDTOS condutorDTOS) {

        return service.update(id, condutorDTOS);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return  service.delete(id);
    }
//    }




}
