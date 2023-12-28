package project.bankapp.dto.requests;

import lombok.Builder;
import lombok.Data;
import project.bankapp.dto.models.BillModel;
import project.bankapp.dto.models.TransactionModel;
import project.bankapp.enums.TransactionType;

import java.util.Optional;

@Data
@Builder
public class TransactionsGetResponse {
    private String fromCard;
    private String toCard;
    private Long value;
    private TransactionType transactionType;

    public static TransactionsGetResponse fromTransactionModel(TransactionModel transactionModel){
        return TransactionsGetResponse.builder()
                .fromCard((Optional.ofNullable(transactionModel.getFromBillEntity()))
                        .map(BillModel::getCardNumber)
                        .orElse(null))
                .transactionType(transactionModel.getTransactionType())
                .value(transactionModel.getValue())
                .toCard(transactionModel.getToBillEntity().getCardNumber())
                .build();
    }
}
