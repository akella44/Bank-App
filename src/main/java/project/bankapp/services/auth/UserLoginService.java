package project.bankapp.services.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.bankapp.dao.UserDao;
import project.bankapp.dto.models.TokenModel;
import project.bankapp.dto.models.UserModel;
import project.bankapp.dto.requests.UserLoginRequest;
import project.bankapp.dto.response.LoginResponse;
import project.bankapp.services.security.jwt.JwtService;

import javax.security.auth.login.CredentialException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginService {
    private final UserDao userDao;
    private final JwtService jwtService;
    public LoginResponse login(UserLoginRequest userLoginRequest,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse servletResponse) throws CredentialException {
        if(!userDao.isUserCredsValid(userLoginRequest.getEmail(), userLoginRequest.getPassword())){
            throw new CredentialException("Invalid email or password");
        }
        log.info("user login service: " + userLoginRequest.getEmail());
        UserModel user = userDao.getUserByEmail(userLoginRequest.getEmail());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword(), user.getAuthorities()
        );

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);
        String userJwt = jwtService.generateToken(user);

        return LoginResponse.builder()
                .email(user.getEmail())
                .token(
                        TokenModel.builder()
                                .email(user.getEmail())
                                .token(userJwt)
                                .build()
                )
                .build();
    }
}
