package br.com.uniamerica.estacionamento.controller;

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


//
//    @RequestMapping(value = "/all", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Condutor> findAll( ){
//        return service.findAll();
//    };
//
//    @RequestMapping(value = "/ativo", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public List<Condutor> findAtivo( ){
//        List<Condutor> Condutores = service.findAll();
//        return Condutores.stream().filter(Condutor::isAtivo).collect(Collectors.toList());
//    };
//
//
   @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid  CondutorDTOS condutorDTOS){
        Condutor condutor = new Condutor();
        BeanUtils.copyProperties(condutorDTOS,condutor);

        return service.save(condutor);
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
//    @RequestMapping(value = "/atualiza", method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE )
//
//    public ResponseEntity<?> update(@RequestBody Condutor Condutor){
//        this.service.update(Condutor);
//        return ResponseEntity.ok("registro atualizado");
//    }
//
//
//
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletar(@PathVariable final Long id) {
//        Optional<Condutor> optionalCondutor = this.service.findById(id);
//
//        if(optionalCondutor.isPresent()) {
//            Condutor condutor = optionalCondutor.get();
//            if (movimentacaoRepository.findByCondutorId(id) == null){
//
//                this.service.deletar(condutor.getId());
//                return ResponseEntity.ok().build();
//            } else {
//
//                condutor.setAtivo(false);
//                this.service.update(condutor);
//                return ResponseEntity.ok().build();
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }




}
