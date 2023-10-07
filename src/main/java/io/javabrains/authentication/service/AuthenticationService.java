package io.javabrains.authentication.service;

import io.javabrains.authentication.entity.UserCredentials;
import io.javabrains.authentication.model.AuthenticatedRequest;
import io.javabrains.authentication.model.User;
import io.javabrains.authentication.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.passwordEncoder = passwordEncoder;
        this.userCredentialRepository = userCredentialRepository;
        this.jwtService = jwtService;
    }

    public String saveUser(User user){
        UserCredentials userCredentials = UserCredentials.builder()
                .email(user.getUserName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userCredentialRepository.save(userCredentials);
        return user.getUserName()+" has been added";
    }

    public String getToken(AuthenticatedRequest request){
        return jwtService.getToken(request.getUserName());
    }
}
