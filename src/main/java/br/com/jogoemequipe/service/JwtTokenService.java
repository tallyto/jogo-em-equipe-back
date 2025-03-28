package br.com.jogoemequipe.service;

import br.com.jogoemequipe.dto.TokenDTO;
import br.com.jogoemequipe.model.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class JwtTokenService {
    @Value("${security.jwt.token.secret}")
    private String SECRET_KEY;
    @Value("${security.jwt.token.time}")
    private Long VALIDITY_TIME;

    public TokenDTO createAccessToken(UUID id, String email, String nome, Set<Role> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_TIME);
        String accessToken = getAccessToken(id, email, nome, roles, now, validity); // Inclui 'nome'
        String refreshToken = getRefreshToken(id, email, roles, now);

        return new TokenDTO().builder()
                .email(email)
                .authenticated(true)
                .createAt(now)
                .expiresAt(validity)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .nome(nome) // Define o nome no DTO
                .build();
    }

    public String getAccessToken(UUID id, String email, String nome, Set<Role> roles, Date now, Date validity) {
        List<String> rolesAsString = roles.stream()
                .map(r -> r.getDescription().toString())
                .toList();

        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("userId", id.toString())
                .withClaim("roles", rolesAsString)
                .withClaim("nome", nome) // Adiciona o nome como um claim
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getRefreshToken(UUID id, String email, Set<Role> roles, Date now) {
        List<String> rolesAsString = roles.stream()
                .map(r -> r.getDescription().toString())
                .toList();
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + VALIDITY_TIME * 2))
                .withClaim("userId", id.toString())
                .withClaim("roles", rolesAsString)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String formatToken(String token) {
        if (token != null && token.startsWith("Bearer "))
            return token.substring("Bearer ".length());
        return null;
    }

    public DecodedJWT decodedJWT(String token) {
        JWTVerifier jwtVerifier = JWT
                .require(Algorithm.HMAC256(SECRET_KEY))
                .build();
        return jwtVerifier.verify(token);
    }

    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = decodedJWT(token);
        return new Date().before(decodedJWT.getExpiresAt());
    }
}
