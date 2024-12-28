package utez.edu.mx.crochetifyBack.secutiry;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.function.Function;

@Component
public class JWTUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    private Key signatureKey;

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //generarl token con el rol y el correo
    public String generateAccessToken(String email, String role, Long idUser) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("idUser",idUser)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // obtener la firma
    private Key getSignatureKey() {
        if (signatureKey == null) {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            signatureKey = Keys.hmacShaKeyFor(keyBytes);
        }
        return signatureKey;
    }

    // obtener rol del token
    public String getRoleFromToken(String token) {
        return getClaim(token, claims -> claims.get("role", String.class));
    }

    // obtener el correo del token
    public String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
