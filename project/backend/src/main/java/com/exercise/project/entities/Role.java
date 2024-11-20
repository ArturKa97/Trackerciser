package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

