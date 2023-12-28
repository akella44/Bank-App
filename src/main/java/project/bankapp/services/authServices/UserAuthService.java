package project.bankapp.services.authServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.bankapp.dto.requests.UserLoginRequest;
import project.bankapp.dto.requests.UserRegisterRequest;
import project.bankapp.dto.response.LoginResponse;

import javax.security.auth.login.CredentialException;

public interface UserAuthService {
    public LoginResponse login(UserLoginRequest userLoginRequest,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse servletResponse) throws CredentialException;
    public void register(UserRegisterRequest userRegisterRequest);
}
