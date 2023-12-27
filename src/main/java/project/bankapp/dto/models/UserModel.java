package project.bankapp.dto.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.bankapp.entities.UserEntity;
import project.bankapp.enums.UserRole;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
public class UserModel implements UserDetails {
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String password;
    private UserRole role;
    public static UserModel fromEntity(UserEntity userEntity){
        return UserModel.builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .secondName(userEntity.getSecondName())
                .phoneNumber(userEntity.getPhoneNumber())
                .password(userEntity.getPassword())
                .birthDate(userEntity.getBirthDate())
                .role(userEntity.getRole())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
