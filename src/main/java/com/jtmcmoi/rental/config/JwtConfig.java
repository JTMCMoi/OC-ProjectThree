package com.jtmcmoi.rental.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
public class JwtConfig {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Bean
    public JwtEncoder jwtEncoder() {
        
        byte[] secret = jwtSecret.getBytes(StandardCharsets.UTF_8);

        if (secret.length < 32)
        {
            throw new IllegalStateException("JWT_SECRET must be at least 32 bytes for HS256");
        }

        OctetSequenceKey jwk = new OctetSequenceKey.Builder(secret)
            .keyUse(KeyUse.SIGNATURE)
            .algorithm(JWSAlgorithm.HS256)
            .keyID("rental-hmac")
        .build();

        ImmutableJWKSet<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwkSource);

    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey secretKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

}
