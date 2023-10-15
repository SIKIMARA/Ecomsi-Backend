package com.javatechie.controller;

import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.UserCredential;
import com.javatechie.repository.UserCredentialRepository;
import com.javatechie.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserCredentialRepository repository;

    @PostMapping("/register")
    public RegisterResponse addNewUser(@RequestBody UserCredential user) {
        if(repository.existsByName(user.getName())) {
            return new RegisterResponse("User already exists", "error");
        }
        else{
            service.saveUser(user);
            return new RegisterResponse("User added to the system", "success");
        }
    }

    @PostMapping("/token")
    public ResponseToken getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return ResponseToken.builder().token(service.generateToken(authRequest.getUsername())).build();
//            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }
   @GetMapping("/all")
    public Iterable<UserCredential> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        service.validateToken(token);
        return "Token is valid";
    }

    private class RegisterResponse {
private String message;
private String status;

        public RegisterResponse(String message, String status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }
        public String getStatus() {
            return status;
        }
    }
}
