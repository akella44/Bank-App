package project.bankapp.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Builder
public class UserRegisterRequest {
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String password;
}
