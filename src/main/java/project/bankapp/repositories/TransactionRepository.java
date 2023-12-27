package project.bankapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bankapp.entities.TransactionEntity;
import project.bankapp.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findByFromUserEntity(UserEntity userEntity);
    List<TransactionEntity> findByToUserEntity(UserEntity userEntity);
}
