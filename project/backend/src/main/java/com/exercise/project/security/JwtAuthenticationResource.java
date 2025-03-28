package com.exercise.project.security;

import com.exercise.project.dtos.LoginAndRegisterRequest;
import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.mappers.UserDTOMapper;
import com.exercise.project.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Login And Authentication")
public class JwtAuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final JwtDecoder jwtDecoder;

    @SecurityRequirements
    @Operation(description = "Post endpoint for User login and authentication",
            summary = "Login and authenticate the user,and assign a JWT token along with JWT refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returns an AuthenticationResponse with userDTO, JWT token, and refresh token in the HTTPOnly cookie",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "404", description = "User was not found with provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed user authentication, bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Credentials of the user that wants to login",
            required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginAndRegisterRequest.class)))
                                                               @RequestBody LoginAndRegisterRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        String token = createToken(authentication);
        String refreshToken = createRefreshToken(authentication);

        User fetchedUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User with username [%s] not found".formatted(authentication.getName())));

        UserDTO userDTO = userDTOMapper.toDTO(fetchedUser);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .domain(".trackerciser.com")
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("None")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new AuthenticationResponse(token, userDTO));
    }

    @SecurityRequirements
    @Operation(description = "Post endpoint for refreshing User's expired JWT token",
            summary = "Refreshes users expired original access token if it's valid and provides a new one if refresh token is still valid and not expired")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returns an AuthenticationResponse with userDTO and new JWT token",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "User is not logged in, conditions for this endpoint not met", content = @Content),
            @ApiResponse(responseCode = "404", description = "Refresh token missing or invalid, user was not found by username", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refresh_token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));

        Jwt jwt;
        try {
            jwt = jwtDecoder.decode(refreshToken);
        } catch (JwtException e) {
            throw new IllegalArgumentException("Refresh token is invalid or expired");
        }
        Map<String, Object> claims = jwt.getClaims();

        String username = (String) claims.get("sub");
        User fetchedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, fetchedUser.getAuthorities());
        String newAccessToken = createToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(newAccessToken, userDTOMapper.toDTO(fetchedUser)));
    }

    @Operation(description = "Post endpoint for User logout",
            summary = "Logs out the user completely deleting the refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returns an response that clears the refresh token",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/logoutUser")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(true)
                .domain(".trackerciser.com")
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message", "Logged out successfully"));
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
    private String createRefreshToken(Authentication authentication) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 60 * 24 * 7))
                .subject(authentication.getName())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

}
