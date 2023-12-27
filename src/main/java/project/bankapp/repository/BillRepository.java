package project.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bankapp.entities.BillEntity;
import project.bankapp.entities.UserEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, UUID> {
    List<BillEntity> findByOwner(UserEntity owner);
}
