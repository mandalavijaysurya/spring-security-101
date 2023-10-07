package io.javabrains.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.javabrains.authentication.entity.UserCredentials;
import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredentials, Integer> {

    Optional<UserCredentials> findByEmail(String userName);
}
