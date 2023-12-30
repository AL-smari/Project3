package com.example.project3.Controller;

import com.example.project3.DTOs.EmployeeDTO;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee());
    }
    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body("employee added");
    }
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body("employee update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("employee delete");
    }
}
