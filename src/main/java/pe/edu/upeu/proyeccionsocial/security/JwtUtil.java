package pe.edu.upeu.proyeccionsocial.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ⚠️ IMPORTANTE: CAMBIA ESTA CLAVE SECRETA POR UNA MÁS SEGURA EN PRODUCCIÓN.
    // Debe tener al menos 256 bits (32 caracteres)
    private static final String SECRET_KEY = "ClaveSecretaParaJWTDeProyeccionSocial1234567891234567890";
    private static final long EXPIRATION_MS = 86400000; // 24 horas

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generarToken(String email) {
        return Jwts.builder()
                .setSubject(email) // El usuario (email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String obtenerEmailDelToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log para debug: e.printStackTrace();
            return false; // Token expirado o inválido
        }
    }
}