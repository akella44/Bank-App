package project.bankapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private BillEntity fromBillEntity;
    @OneToOne
    private BillEntity toBillEntity;
    @OneToOne
    private UserEntity fromUserEntity;
    @OneToOne
    private UserEntity toUserEntity;
    private Long value;
}
