package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTOs.AccountDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repoaitory.AccountRepository;
import com.example.project3.Repoaitory.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Internal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private  final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;


    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }


    public void addAccount(AccountDTO accountDTO){
        Customer customer = customerRepository.findCustomerById(accountDTO.getCustomer_user_id());
        if(customer==null){
            throw new ApiException("customer id not found");
        }
        Account account = new Account(null, accountDTO.getAccountNumber(), accountDTO.getBalance(),accountDTO.isActive(),customer);
        accountRepository.save(account);
    }

    public void updateAccount(AccountDTO accountDTO){
        Account account=accountRepository.findAccountById(accountDTO.getCustomer_user_id());
        if(account==null){
            throw new ApiException("account id not found");
        }
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setActive(accountDTO.isActive());
        accountRepository.save(account);
    }


    public void deleteAccount(Integer id){
        Account account = accountRepository.findAccountById(id);
        if(account==null){
            throw new ApiException("account id not found");
        }
        accountRepository.delete(account);
    }

    public void activeAccount(Integer id){
        Account account = accountRepository.findAccountById(id);
        if(account==null){
            throw new ApiException("account id not found");
        }
        account.setActive(true);
        accountRepository.save(account);

    }
    public void BlockAccount(Integer id){
        Account account = accountRepository.findAccountById(id);
        if(account==null){
            throw new ApiException("account id not found");
        }
        account.setActive(false);
        accountRepository.save(account);

    }

    public Account getMyAccount(Integer auth){
        Account account = accountRepository.findAccountById(auth);
        if(account==null){
            throw new ApiException("account id not found");
        }
        return account;
    }

    public void withdraw(Integer auth,double amount){
        Account account = accountRepository.findAccountById(auth);
        if(account==null){
            throw new ApiException("account id not found");
        }
       account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }
    public void deposit(Integer auth,double amount){
        Account account = accountRepository.findAccountById(auth);
        if(account==null){
            throw new ApiException("account id not found");
        }
        if(account.getBalance()<amount){
            throw new ApiException("there is no enough money");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }

    public void transfer(Integer auth,Integer id,double amount){
        Account account = accountRepository.findAccountById(auth);
        if(account==null){
            throw new ApiException("account id not found");
        }
        Account toAccount = accountRepository.findAccountById(id);
        if(toAccount==null){
            throw new ApiException("account id not found");
        }
        if(account.getBalance()<amount){
            throw new ApiException("not enough money");
        }

        account.setBalance(account.getBalance()-amount);
        toAccount.setBalance(toAccount.getBalance()+amount);
        accountRepository.save(account);
        accountRepository.save(toAccount);


    }
}
