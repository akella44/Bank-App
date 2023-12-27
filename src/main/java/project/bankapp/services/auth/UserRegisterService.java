package project.bankapp.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.bankapp.dao.UserDao;
import project.bankapp.dto.models.UserModel;
import project.bankapp.dto.requests.UserRegisterRequest;
import project.bankapp.enums.UserRole;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserDao userDao;
    public void register(UserRegisterRequest userRegisterRequest){
        UserModel userModel = UserModel.builder()
                .email(userRegisterRequest.getEmail())
                .firstName(userRegisterRequest.getFirstName())
                .secondName(userRegisterRequest.getSecondName())
                .phoneNumber(userRegisterRequest.getPhoneNumber())
                .birthDate(userRegisterRequest.getBirthDate())
                .password(userRegisterRequest.getPassword())
                .role(UserRole.USER)
                .build();
        userDao.addNewUser(userModel);
    }
}
