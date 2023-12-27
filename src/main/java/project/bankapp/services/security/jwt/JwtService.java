package project.bankapp.services.security.jwt;

import project.bankapp.dto.models.UserModel;

public interface JwtService {
    UserModel parseToken(String jwt);
    String generateToken(UserModel userModel);
}
