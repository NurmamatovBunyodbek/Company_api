package uz.pdp.company_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.company_api.Service.WorkerService;
import uz.pdp.company_api.entity.Worker;
import uz.pdp.company_api.payload.Result;
import uz.pdp.company_api.payload.WorkerDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;


    @GetMapping
    public List<Worker> workerList()
    {
        List<Worker> workers = workerService.workers();
        return workers;
    }
    @GetMapping("/{id}")
    public  Worker getId(@PathVariable Integer id){
        Worker workerID = workerService.getWorkerID(id);
        return workerID;
    }

    @PostMapping
    public ResponseEntity<Result> addWorker(@Valid  @RequestBody WorkerDto workerDto){
        Result result = workerService.addWorker(workerDto);
        if (result.isSuccess()){
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity< Result> editWorker(@PathVariable Integer id , @Valid @RequestBody WorkerDto workerDto){
        Result result = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.CONFLICT:HttpStatus.CONFLICT).body(result);
    }
    @DeleteMapping("/{id}")
    public  Result deleted(@PathVariable Integer id){
        Result result = workerService.deletedWorker(id);
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
