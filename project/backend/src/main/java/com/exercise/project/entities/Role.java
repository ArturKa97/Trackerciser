package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Entity
@EqualsAndHashCode(of = "role")
public class Role implements GrantedAuthority {

    private static final String SPRING_SECURITY_AUTHORITY_PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    @NotNull(message = "Role value cannot be null")
    @NotBlank(message = "Role value cannot be empty")
    @Size(min = 1, max = 30, message = "Role value must be between {min} and {max} characters long")
    private String role;

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<UserRole> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return SPRING_SECURITY_AUTHORITY_PREFIX + role;
    }
}

