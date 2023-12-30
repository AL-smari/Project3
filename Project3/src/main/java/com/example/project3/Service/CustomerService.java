package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTOs.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repoaitory.AuthRepository;
import com.example.project3.Repoaitory.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void addCustomer(CustomerDTO customerDTO){
        User user = authRepository.findUserById(customerDTO.getUser_id());
        if(user==null){
            throw new ApiException("user not found");
        }
        Customer customer = new Customer(null, customerDTO.getPhoneNumber(),user,null);
        customerRepository.save(customer);

    }

    public void updateCustomer(CustomerDTO customerDTO){
        Customer customer = customerRepository.findCustomerById(customerDTO.getUser_id());
        if(customer==null){
            throw new ApiException("customer id not found");
        }

        customer.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(customer);
    }


    public void deleteCustomer(Integer id){
        Customer customer =customerRepository.findCustomerById(id);
        if(customer==null){
            throw new ApiException("customer id not found");
        }
        customerRepository.delete(customer);
    }
}
