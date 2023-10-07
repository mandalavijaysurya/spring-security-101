package io.javabrains.authentication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "UserCredentials")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usercredid")
    private Integer id;
    private String email;
    private String password;
}
