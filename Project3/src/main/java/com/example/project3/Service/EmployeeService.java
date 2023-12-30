package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTOs.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repoaitory.AuthRepository;
import com.example.project3.Repoaitory.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;


    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        User user = authRepository.findUserById(employeeDTO.getUser_id());

        if(user==null){
            throw new ApiException("user id not found");
        }

        Employee employee = new Employee(null, employeeDTO.getPosition(),employeeDTO.getSalary(),user);
        employeeRepository.save(employee);
    }
    public void updateEmployee(EmployeeDTO employeeDTO){

        Employee employee = employeeRepository.findEmployeeById(employeeDTO.getUser_id());
        if(employee==null){
            throw new ApiException("employee id not found");
        }
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(employee);

    }

    public void  deleteEmployee(Integer id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if(employee==null){
            throw new ApiException("employee id not found");
        }
        employeeRepository.delete(employee);
    }
}
