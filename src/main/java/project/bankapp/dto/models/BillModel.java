package project.bankapp.dto.models;


import lombok.Builder;
import lombok.Data;
import project.bankapp.entities.BillEntity;
import project.bankapp.enums.CurrencyType;

import java.util.UUID;
@Data
@Builder
public class BillModel {
    private UserModel owner;
    private CurrencyType currency;
    private Long value;
    private String cardNumber;
    public static BillModel fromEntity(BillEntity billEntity){
        return BillModel.builder()
                .owner(UserModel.fromEntity(billEntity.getOwner()))
                .currency(billEntity.getCurrency())
                .value(billEntity.getValue())
                .cardNumber(billEntity.getCardNumber())
                .build();
    }
}
