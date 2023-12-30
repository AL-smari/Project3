package com.example.project3.Controller;

import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user){
        authService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body("user register");
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(HttpStatus.OK).body("login");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(HttpStatus.OK).body("logout");
    }

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(authService.getUsers());
    }
    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User auth , @Valid@RequestBody User user ){
        authService.updateUser(auth.getId(),user);
        return ResponseEntity.status(HttpStatus.OK).body("user updated");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User auth ){
        authService.deleteUser(auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("user deleted");
    }
}

