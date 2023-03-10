package uz.pdp.company_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.company_api.Service.DeportmentService;
import uz.pdp.company_api.entity.Deportment;
import uz.pdp.company_api.payload.DeportmentDto;
import uz.pdp.company_api.payload.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class DeportmentController {

    @Autowired
    DeportmentService deportmentService;

    @GetMapping
    public List<Deportment > deportments(){
        List<Deportment> list = deportmentService.deportmentList();
        return list;
    }
    @GetMapping("/{id}")
    public Deportment getDeport(@PathVariable Integer id){
        Deportment deportmentId = deportmentService.getDeportmentId(id);
        return deportmentId;
    }
    @PostMapping
    public ResponseEntity< Result > addDeportment(@Valid @RequestBody DeportmentDto deportmentDto){
        Result result = deportmentService.addDeportment(deportmentDto);
        if (result.isSuccess()){
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity< Result> update(@PathVariable Integer id,@Valid @RequestBody DeportmentDto deportmentDto){
        Result result = deportmentService.editDeportment(id, deportmentDto);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.CONFLICT:HttpStatus.CONFLICT).body(result);
    }
    @DeleteMapping("/{id}")
    public  Result deleted(@PathVariable Integer id){
        Result result = deportmentService.deletedId(id);
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
