package project.bankapp.services.securityServices.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import project.bankapp.dao.UserDao;
import project.bankapp.dto.models.UserModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.bankapp.enums.UserRole;
import project.bankapp.properties.TokenProperties;

import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final TokenProperties tokenProperties;
    private final UserDao userDao;
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

    public UserModel getUserModelByReqWithToken(HttpServletRequest request){
        //todo
        //exception here if jwt is null
        String jwt = getToken(request);
        UserModel user = parseToken(jwt);
        return  userDao.getUserByEmail(user.getEmail());
    }
    //todo
    //code repetition (same method in jwtfilter)
    private String getToken(HttpServletRequest request) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            return null;
        }

        final String[] strs = header.split(" ");
        if (strs.length != 2) {
            return null;
        }

        return strs[1].trim();
    }
    private Date getExpirationDate() {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        calendar.add(Calendar.MINUTE, tokenProperties.getTimeToLive());

        log.info(calendar.getTime().toString());
        return calendar.getTime();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Base64.getDecoder().decode(tokenProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}