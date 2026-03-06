package com.jtmcmoi.rental.service;

import java.time.Instant;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(String subject) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("rental-api")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(300))
            .subject(subject)
        .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256)
            .keyId("rental-hmac")
        .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();

    }

}
