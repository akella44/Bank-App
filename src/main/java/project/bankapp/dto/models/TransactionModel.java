package project.bankapp.dto.models;

import lombok.Builder;
import lombok.Data;
import project.bankapp.entities.TransactionEntity;
import project.bankapp.enums.TransactionType;

import java.util.Optional;

@Data
@Builder
public class TransactionModel {
    private BillModel fromBillEntity;
    private BillModel toBillEntity;
    private UserModel fromUserEntity;
    private UserModel toUserEntity;
    private Long value;
    private TransactionType transactionType;

    public static TransactionModel fromEntity(TransactionEntity transactionEntity){
        return TransactionModel.builder()
                //when transactionType is REPLENISHMENT, fromBill is null
                .fromBillEntity((Optional.ofNullable(transactionEntity.getFromBillEntity())
                        .map(BillModel::fromEntity)
                        .orElse(null)))
                .toBillEntity(BillModel.fromEntity(transactionEntity.getToBillEntity()))
                .transactionType(transactionEntity.getTransactionType())
                .fromUserEntity((Optional.ofNullable(transactionEntity.getFromUserEntity()))
                        .map(UserModel::fromEntity)
                        .orElse(null))
                .toUserEntity(UserModel.fromEntity(transactionEntity.getToUserEntity()))
                .value(transactionEntity.getValue())
                .build();
    }
}
