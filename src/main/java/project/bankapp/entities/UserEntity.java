package project.bankapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String secondName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private LocalDate birthDate;
}
