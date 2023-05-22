package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.controller.exeption.NotFoundException;
import br.com.uniamerica.estacionamento.dtos.ModeloDTOS;

import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private ModeloService service;

    @Autowired
    MarcaRepository marcarepository;

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

    @RequestMapping(value = "/ativos", method = RequestMethod.GET)
    public ResponseEntity<?> findAtivo( ){
        return service.findAtivo();
    };

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid  ModeloDTOS modeloDTOS){

        return service.create(modeloDTOS);
       //return  ResponseEntity.ok().body(modeloDTOS);
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
    public ResponseEntity<?> updateEntity(@PathVariable Long id, @RequestBody  @Valid ModeloDTOS modeloDTOS) {

        return service.update(id, modeloDTOS);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return  service.delete(id);
    }
//    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setMessage("Foreign key constraint violation");
//        errorResponse.setDetalhe(ex.getCause().getMessage());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }



}
