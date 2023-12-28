package project.bankapp.dto.requests;

import lombok.Builder;
import lombok.Data;
import project.bankapp.dto.models.BillModel;
import project.bankapp.enums.CurrencyType;

@Data
@Builder
public class BillsGetResponse {
    private String ownerEmail;
    private String FirstName;
    private String SecondName;
    private CurrencyType currencyType;
    private Long value;
    private String cardNumber;
    public static BillsGetResponse fromBillModel(BillModel billModel){
        return BillsGetResponse.builder()
                .ownerEmail(billModel.getOwner().getEmail())
                .FirstName(billModel.getOwner().getFirstName())
                .SecondName(billModel.getOwner().getSecondName())
                .cardNumber(billModel.getCardNumber())
                .currencyType(billModel.getCurrency())
                .value(billModel.getValue())
                .build();
    }
}
