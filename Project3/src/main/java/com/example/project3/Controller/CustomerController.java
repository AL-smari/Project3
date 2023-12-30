package com.example.project3.Controller;

import com.example.project3.DTOs.CustomerDTO;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getCustomer(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }
    @PostMapping("/add")
    public ResponseEntity addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("customer added");
    }
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@Valid@RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("customer update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("customer delete");
    }
}
