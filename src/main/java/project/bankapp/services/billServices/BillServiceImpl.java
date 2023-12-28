package project.bankapp.services.billServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.bankapp.dao.BillDao;
import project.bankapp.dto.models.BillModel;
import project.bankapp.dto.models.UserModel;
import project.bankapp.dto.requests.BillCreationRequest;
import project.bankapp.dto.requests.BillsGetResponse;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService{
    private final BillDao billDao;
    private final CardNumberGenerator cardNumberGenerator;
    @Override
    public void createBill(BillCreationRequest billCreationRequest, UserModel owner) {
        //todo
        //validation if here is same card number
        billDao.createNewBill(
                BillModel.builder()
                        .value(Long.valueOf("0"))
                        .owner(owner)
                        .currency(billCreationRequest.currencyType)
                        .cardNumber(cardNumberGenerator.generate("5531", 16))
                        .build()
        );
    }

    @Override
    public List<BillsGetResponse> getUserBills(String email) {
        return billDao.getUserBills(email).stream()
                .map(BillsGetResponse::fromBillModel)
                .toList();
    }

}


