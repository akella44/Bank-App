package project.bankapp.model;

import java.util.UUID;

public class TransactionModel {
    private UUID id;
    private BillModel fromBillEntity;
    private BillModel toBillEntity;
    private UserModel fromUserEntity;
    private UserModel toUserEntity;
    private Long value;
}
