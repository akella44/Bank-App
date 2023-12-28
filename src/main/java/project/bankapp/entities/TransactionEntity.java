package project.bankapp.entities;

import jakarta.persistence.*;
import lombok.*;
import project.bankapp.enums.TransactionType;

import java.util.UUID;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private BillEntity fromBillEntity;
    @ManyToOne
    private BillEntity toBillEntity;
    @ManyToOne
    private UserEntity fromUserEntity;
    @ManyToOne
    private UserEntity toUserEntity;
    @Column(nullable = false)
    private Long value;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
