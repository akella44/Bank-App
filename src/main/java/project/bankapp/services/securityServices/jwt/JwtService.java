package project.bankapp.services.securityServices.jwt;

import jakarta.servlet.http.HttpServletRequest;
import project.bankapp.dto.models.UserModel;

public interface JwtService {
    UserModel parseToken(String jwt);
    String generateToken(UserModel userModel);
    UserModel getUserModelByReqWithToken(HttpServletRequest request);
}
