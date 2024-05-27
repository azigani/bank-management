package com.alphonse.bankback.securite.jwt;

import com.alphonse.bankback.securite.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${com.alphonse.app.jwtSecret}")
    private String jwtSecret;
    @Value("${com.alphonse.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public JwtUtils() {
    }

    public String generateJwtToken(Authentication authentication) {
      UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().
                setSubject(userPrincipal.getUsername()
                ).setIssuedAt(new Date()).
                setExpiration(new Date((new Date()).getTime() +
                        (long) this.jwtExpirationMs)).signWith(SignatureAlgorithm.HS512,
                this.jwtSecret).compact();

    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parser().setSigningKey(this.jwtSecret)
                .parseClaimsJws(token).getBody();
    }

    public String getUserNameFromJwtToken(String token) {
        return ((Claims) Jwts.parser().setSigningKey(this.jwtSecret)
                .parseClaimsJws(token).getBody())
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException var3) {
            logger.error("Signature JWT invalide : {}",
                    var3.getMessage());
        } catch (MalformedJwtException var4) {
            logger.error(" Token JWT Invalid : {}",
                    var4.getMessage());
        } catch (ExpiredJwtException var5) {
            logger.error("Token JWT  expiré: {}",
                    var5.getMessage());
        } catch (UnsupportedJwtException var6) {
            logger.error("Token JWT  non supporté: {}",
                    var6.getMessage());
        } catch (IllegalArgumentException var7) {
            logger.error("Les clefs JWT sont vides: {}",
                    var7.getMessage());
        }

        return false;
    }


}
