package com.duoc.pedidos.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

    @Service
    public class JwtService {

        @Value("${jwt.secret}")
        private String secret;

        public String extraerUsername(String token) {
            return extraerClaims(token).getSubject();
        }

        public boolean esTokenValido(String token) {
            try {
                extraerClaims(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
            private Claims extraerClaims(String token) {
                return Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            }
        private SecretKey getSigningKey() {
            return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        }
    }

