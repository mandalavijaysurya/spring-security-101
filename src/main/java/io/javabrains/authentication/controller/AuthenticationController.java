package io.javabrains.authentication.controller;

import io.javabrains.authentication.model.AuthenticatedRequest;
import io.javabrains.authentication.model.User;
import io.javabrains.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "<h2>Welcome</h2>";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        return authService.saveUser(user);
    }

    @PostMapping("/tkn")
    public String generateToken(@RequestBody AuthenticatedRequest request){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        if(authentication.isAuthenticated()) {
            return authService.getToken(request);
        }
        return "Invalid access";
    }
}
