package project.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.bankapp.dto.models.BillModel;
import project.bankapp.entities.BillEntity;
import project.bankapp.exceptions.InvalidArgException;
import project.bankapp.exceptions.InvalidOpertaionException;
import project.bankapp.repositories.BillRepository;
import project.bankapp.repositories.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BillDao {
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    public List<BillModel> getUserBills(String email){
        List<BillEntity> billEntities = billRepository.findByOwner(userRepository.findByEmail(email));
        //convert to List<BillModeL> and return
        return billEntities.stream()
                .map(BillModel::fromEntity)
                .toList();
    }
    public List<String> getUserCards(String email){
        return getUserBills(email).stream()
                .map(BillModel::getCardNumber)
                .toList();
    }
    public void createNewBill(BillModel billModel){
        BillEntity billEntity = BillEntity.builder()
                .id(UUID.randomUUID())
                //getting UserEntity with userRep
                .owner(userRepository.findByEmail(billModel.getOwner().getEmail()))
                .value(billModel.getValue())
                .currency(billModel.getCurrency())
                .cardNumber(billModel.getCardNumber())
                .build();
        billRepository.save(billEntity);
    }
    public void increaseBillBalance(Long value, String cardNumber) throws InvalidArgException {
        BillEntity billEntity = billRepository.findByCardNumber(cardNumber);

        if(!(value >= 0))
            throw new InvalidArgException("The replenishment amount cannot be negative");

        billEntity.setValue(billEntity.getValue() + value);
        billRepository.save(billEntity);
    }

    public void decreaseBillBalance(Long value, String cardNumber) throws InvalidOpertaionException {
        BillEntity billEntity = billRepository.findByCardNumber(cardNumber);

        if(!(billEntity.getValue() - value >= 0))
            throw new InvalidOpertaionException();

        billEntity.setValue(billEntity.getValue() - value);
        billRepository.save(billEntity);
    }
    public Boolean isBillExistByCard(String cardNumber){
        return billRepository.existsByCardNumber(cardNumber);
    }
    public BillModel getBillByCard(String cardNumber){
        return BillModel.fromEntity(billRepository.findByCardNumber(cardNumber));
    }
}
