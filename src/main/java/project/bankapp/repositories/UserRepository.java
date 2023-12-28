package project.bankapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bankapp.entities.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
    Boolean existsByEmail(String email);
}
