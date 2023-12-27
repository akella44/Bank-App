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
import project.bankapp.services.auth.UserLoginService;
import project.bankapp.services.auth.UserRegisterService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserRegisterService userRegisterService;
   @PostMapping("/register")
    HttpStatus userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
       try {
           userRegisterService.register(userRegisterRequest);
           log.info("New registration request: " + userRegisterRequest.toString());
           return HttpStatus.CREATED;
       }
       catch (org.springframework.dao.DataIntegrityViolationException ex){
           return HttpStatus.BAD_REQUEST;
       }
       catch (Exception ex){
           return HttpStatus.INTERNAL_SERVER_ERROR;
       }
   }

   private final UserLoginService userLoginService;
    @PostMapping("/login")
    HttpStatus userLogin(@RequestBody UserLoginRequest userLoginRequest,
                         HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse){
        try{
            LoginResponse response = userLoginService.login(userLoginRequest, httpServletRequest,
                    httpServletResponse);
            httpServletResponse.addCookie(new Cookie("jwt", response.getToken().getToken()));
            return HttpStatus.OK;
        }
        catch (Exception ex){
            ex.printStackTrace();
            log.info(ex.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
