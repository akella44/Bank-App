package project.bankapp.services.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import project.bankapp.dto.models.UserModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.bankapp.enums.UserRole;
import project.bankapp.properties.TokenProperties;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final TokenProperties tokenProperties;

    @Override
    public UserModel parseToken(String jwt) {
        try {

            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build();

            Jws<Claims> parsedJwt = jwtParser.parseClaimsJws(jwt);

            Claims claims = parsedJwt.getBody();

            return UserModel.builder()
                    .email(claims.getSubject())
                    .role(UserRole.valueOf((String) claims.get("role")))
                    .build();
        } catch (ExpiredJwtException e){
            log.info("Jwt is expired: {}", e.getMessage());
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
        }
    }

    @Override
    public String generateToken(UserModel userModel) {
        Claims claims = Jwts.claims();
        claims.setSubject(userModel.getEmail());
        claims.put("role", userModel.getRole().getAuthority());
        claims.setExpiration(getExpirationDate());

        return Jwts.builder()
                .addClaims(claims)
                .signWith(getSigninKey())
                .compact();
    }


    private Date getExpirationDate() {
        Date now = new Date();
        now.setTime(now.getTime() + 100);
        return now;
    }

    private Key getSigninKey() {
        log.info("Prop: " + tokenProperties.getSecret());
        byte[] keyBytes = Base64.getDecoder().decode(tokenProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}