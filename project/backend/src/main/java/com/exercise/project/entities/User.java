package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@Table(name = "`user`")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull(message = "Username value cannot be null")
    @NotBlank(message = "Username value cannot be empty")
    @Size(min = 1, max = 20, message = "Username value must be between {min} and {max} characters long")
    //Might add a regex expression
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password value cannot be null")
    @NotBlank(message = "Password value cannot be empty")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()=-]).{8,64}$",
            message = "Password must be between 8 and 64 characters long, and include at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WorkoutSession> userWorkoutSessions = new ArrayList<>();

    public void addWorkoutSession(WorkoutSession workoutSession) {
        userWorkoutSessions.add(workoutSession);
        workoutSession.setUser(this);
    }

    public void removeWorkoutSession(WorkoutSession workoutSession) {
        userWorkoutSessions.remove(workoutSession);
        workoutSession.setUser(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(UserRole::getRole)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
