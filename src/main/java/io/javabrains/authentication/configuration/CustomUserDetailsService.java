package io.javabrains.authentication.configuration;

import io.javabrains.authentication.entity.UserCredentials;
import io.javabrains.authentication.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserCredentialRepository userCredentialRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> userCredentials = userCredentialRepository.findByEmail(username);
        return userCredentials.map(CustomUserDetails::new).orElseThrow(() ->new UsernameNotFoundException(username+" was not found"));
    }
}
