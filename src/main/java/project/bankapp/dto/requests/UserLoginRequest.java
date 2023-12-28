package project.bankapp.dto.requests;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String email;

    private String password;

}