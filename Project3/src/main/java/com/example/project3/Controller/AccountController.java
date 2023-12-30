package com.example.project3.Controller;

import com.example.project3.DTOs.AccountDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/get")
    public ResponseEntity getAccount(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccounts());
    }
    @PostMapping("/add")
    public ResponseEntity addAccount(@Valid@RequestBody AccountDTO accountDTO){
        accountService.addAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.OK).body("account added");
    }
    @PutMapping("/update")
    public ResponseEntity updateAccount(@Valid @RequestBody AccountDTO accountDTO){
        accountService.updateAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.OK).body("account update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body("account delete");
    }
    @PutMapping("/activate/{id}")
    public ResponseEntity activateAccount(@PathVariable Integer id){
        accountService.activeAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body("account activated");
    }

    @PutMapping("/block/{id}")
    public ResponseEntity blockAccount(@PathVariable Integer id){
        accountService.BlockAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body("account blocked");
    }
    @GetMapping("/getMyAccount")
    public ResponseEntity getMyAccount(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccount(user.getId()));
    }
    @PutMapping("/withdraw/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user,@PathVariable double amount){
        accountService.withdraw(user.getId(),amount);
        return ResponseEntity.status(HttpStatus.OK).body("withdraw");
    }
    @PutMapping("/deposit/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user,@PathVariable double amount){
        accountService.deposit(user.getId(),amount);
        return ResponseEntity.status(HttpStatus.OK).body("deposit");
    }
    @PutMapping("/transfer/{id}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User user,@PathVariable Integer id,@PathVariable double amount){
        accountService.transfer(user.getId(), id,amount);
        return ResponseEntity.status(HttpStatus.OK).body("transfer");
    }

}
