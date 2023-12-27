package project.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bankapp.entities.TransactionEntity;
import project.bankapp.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findByFromUser(UserEntity userEntity);
    List<TransactionEntity> findByToUser(UserEntity userEntity);
}
