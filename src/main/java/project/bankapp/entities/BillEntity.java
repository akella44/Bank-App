package project.bankapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    private String currency;
    @Column(nullable = false)
    private Long value;
}
