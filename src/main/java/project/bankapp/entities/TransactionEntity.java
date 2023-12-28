package project.bankapp.entities;

import jakarta.persistence.*;
import lombok.*;
import project.bankapp.enums.TransactionType;

import java.util.UUID;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @Column(nullable = false)
    private Long value;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
