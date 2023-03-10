package uz.pdp.company_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.company_api.Service.CompanyService;
import uz.pdp.company_api.entity.Company;
import uz.pdp.company_api.payload.CompanyDto;
import uz.pdp.company_api.payload.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public List<Company> companyList() {
        List<Company> companiesList = companyService.companies();
        return companiesList;
    }

    @GetMapping("/{id}")
    public Company getCompanyId(@PathVariable Integer id) {
        Company companyId = companyService.getCompanyId(id);
        return companyId;
    }

    @PostMapping
    public ResponseEntity< Result > addCompany( @Valid @RequestBody CompanyDto companyDto) {
        Result result = companyService.addCompany(companyDto);
        if (result.isSuccess()){
            return  ResponseEntity.status(201).body(result);
        }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity< Result > editingCompany(@PathVariable Integer id, @Valid @RequestBody CompanyDto companyDto) {
        Result result = companyService.editCompany(id, companyDto);
           return ResponseEntity.status(result.isSuccess()?HttpStatus.CONFLICT:HttpStatus.CONFLICT).body(result);
    }

    @DeleteMapping("/{id}")
    public Result deletedCompany(@PathVariable Integer id) {
        Result result = companyService.deletedCompany(id);
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
