package uz.pdp.company_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.company_api.Service.AddressService;
import uz.pdp.company_api.entity.Address;
import uz.pdp.company_api.payload.AddressDto;
import uz.pdp.company_api.payload.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> addressList(){
        List<Address> list = addressService.addressList();
        return list;
    }
    @GetMapping("/{id}")
      public  Address getAddressId(@PathVariable Integer id){
        return addressService.addressId(id);
    }
    @PostMapping
    public ResponseEntity< Result> addAddress( @Valid @RequestBody AddressDto addressDto){
        Result result = addressService.addAddress(addressDto);
        if (result.isSuccess()){
             return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity< Result > editingAddress(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto){
        Result result = addressService.editingAddress(id, addressDto);
          return  ResponseEntity.status(result.isSuccess()?HttpStatus.CONFLICT:HttpStatus.CONFLICT).body(result);
    }
    @DeleteMapping("/{id}")
    public  Result deletedAddress(@PathVariable Integer id ){
        Result result = addressService.deletedAddress(id);
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
