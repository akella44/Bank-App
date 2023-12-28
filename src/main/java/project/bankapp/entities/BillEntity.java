package project.bankapp.entities;

import jakarta.persistence.*;
import lombok.*;
import project.bankapp.enums.CurrencyType;

import java.util.UUID;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "bills")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private UserEntity owner;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    @Column(nullable = false)
    private Long value;
    @Column(unique = true, nullable = false)
    private String cardNumber;
}
