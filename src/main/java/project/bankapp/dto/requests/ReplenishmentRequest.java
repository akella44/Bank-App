package project.bankapp.dto.requests;

import lombok.Data;

@Data
public class ReplenishmentRequest {
    private String cardNumber;
    private Long value;
}
