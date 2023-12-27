package project.bankapp.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.bankapp.dto.models.UserModel;
import project.bankapp.entities.UserEntity;
import project.bankapp.repositories.UserRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserDao {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addNewUser(UserModel userModel){
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID())
                .phoneNumber(userModel.getPhoneNumber())
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .secondName(userModel.getSecondName())
                .role(userModel.getRole())
                .birthDate(userModel.getBirthDate())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .build();
        userRepository.save(userEntity);
    }

    public UserModel getUserByEmail(String email){
        return UserModel.fromEntity(
                userRepository.findByEmail(email)
        );
    }

    public Boolean isUserWithEmailExist(String email){
        return userRepository.findByEmail(email) != null;
    }

    public Boolean isUserCredsValid(String email, String password){
        UserEntity user = userRepository.findByEmail(email);
        log.info("user: " + user.getEmail() + " " + user.getPassword());
        return passwordEncoder.matches(password, user.getPassword());
    }
}
