package project.bankapp.dto.models;

import lombok.Data;

import java.util.UUID;
@Data
public class TransactionModel {
    private UUID id;
    private BillModel fromBillEntity;
    private BillModel toBillEntity;
    private UserModel fromUserEntity;
    private UserModel toUserEntity;
    private Long value;
}
