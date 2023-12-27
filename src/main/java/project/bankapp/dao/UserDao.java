package project.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.bankapp.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserDao {
    private final UserRepository userRepository;


}
