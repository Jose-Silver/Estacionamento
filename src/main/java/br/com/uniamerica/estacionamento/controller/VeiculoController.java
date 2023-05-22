package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.controller.exeption.NotFoundException;
import br.com.uniamerica.estacionamento.dtos.VeiculoDTOS;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.service.VeiculoService;
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
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @Autowired
    private ModeloRepository modeloRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public ResponseEntity<?> findById(
            @PathVariable(value = "id") Long id
    ){
        return service.findById(id);
    };

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }




    @RequestMapping(value = "/all", method = RequestMethod.GET)

    public ResponseEntity<?> findAll( ){
        return service.findAll();
    };
    //
    @RequestMapping(value = "/ativos", method = RequestMethod.GET)
    public ResponseEntity<?> findAtivo( ){
        return service.findAtivo();
    };

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid  VeiculoDTOS veiculoDTOS){
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(modeloRepository.getById(veiculoDTOS.getModelo()));
        BeanUtils.copyProperties(veiculoDTOS,veiculo);

        return service.create(veiculo);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException (MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldname = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldname, errorMessage);
        });

        return errors;
    };

    @ExceptionHandler(br.com.uniamerica.estacionamento.controller.exeption.DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable Long id, @RequestBody  @Valid VeiculoDTOS veiculoDTOS) {

        return service.update(id, veiculoDTOS);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return  service.delete(id);
    }
   }





