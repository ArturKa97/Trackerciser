package com.exercise.project.security;

import com.exercise.project.dtos.LoginRequest;
import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.mappers.UserDTOMapper;
import com.exercise.project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        String token = createToken(authentication);

        User fetchedUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User with username [%s] not found".formatted(authentication.getName())));

        UserDTO userDTO = userDTOMapper.toDTO(fetchedUser);

        return new AuthenticationResponse(token, userDTO);
    }

    private String createToken(Authentication authentication) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 15))
                .subject(authentication.getName())
                .claim("authorities", createAuthorities(authentication))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    private String createAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

}
