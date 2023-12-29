package project.bankapp.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.bankapp.dto.requests.UserLoginRequest;
import project.bankapp.dto.requests.UserRegisterRequest;
import project.bankapp.dto.response.LoginResponse;
import project.bankapp.services.authServices.UserAuthServiceImpl;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserAuthServiceImpl userAuthService;
   @PostMapping("/register")
    HttpStatus userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
       userAuthService.register(userRegisterRequest);
       return HttpStatus.CREATED;
   }
    @PostMapping("/login")
    HttpStatus userLogin(@RequestBody UserLoginRequest userLoginRequest,
                         HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws CredentialException {
        LoginResponse response = userAuthService.login(userLoginRequest, httpServletRequest,
                httpServletResponse);
        httpServletResponse.addCookie(new Cookie("jwt", response.getToken().getToken()));
        return HttpStatus.OK;
    }
}
