package project.bankapp.dto.requests;

import lombok.Data;

@Data
public class CashTransferRequest {
    String fromCard;
    String toCard;
    Long value;
}
